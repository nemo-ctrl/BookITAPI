package com.rijai.BookingAPI.controller;

import com.rijai.BookingAPI.model.*;
import com.rijai.BookingAPI.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private IStaffService staffService;
    @Autowired
    private IBookingService bookingService;

    // SMS GATEWAY (TWILIO)

    @PostMapping("/sms")
    public void sendSms(@RequestParam("to") String to,
                        @RequestParam("from") String from,
                        @RequestParam("body") String body) {
        smsService.sendSms(to, from, body);
    }

    @PostMapping("/sendSms")
    public void sendSms(@RequestBody SmsRequest request) {
        smsService.sendSms(request.getTo(), "+17259770639", request.getBody());
    }

    @GetMapping("/test-sms")
    public ModelAndView testsms(){
        ModelAndView mv = new ModelAndView("Test");
        mv.addObject("test", new User());
        return mv;
    }

    // MODEL ADMIN

    @PostMapping("/admin-login")
    @ResponseBody
    public Map<String, Object> adminlogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Admin foundAdmin = adminService.findByUsernameAndPassword(username, password);
        Map<String, Object> response = new HashMap<>();
        if (foundAdmin != null) {
            System.out.println("Admin found: " + foundAdmin.getRestaurant());
            session.setAttribute("restaurant", foundAdmin.getRestaurant());
            response.put("redirect", "/admin/adminIndex.html");
        } else {
            System.out.println("Admin not found.");
            response.put("error", "Incorrect username or password");
        }
        return response;
    }

    @PostMapping(value="/admin-register")
    public ResponseEntity<Void> addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // MODEL USER

    @PostMapping("/user-login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User foundUser = userService.findByUsernameAndPassword(username, password);
        Map<String, Object> response = new HashMap<>();
        if (foundUser != null) {
            System.out.println("User found: " + foundUser.getName());
            session.setAttribute("userId", foundUser.getId());
            response.put("redirect", "/user/index.html");
        } else {
            System.out.println("User not found.");
            response.put("error", "Incorrect username or password");
        }
        return response;
    }


    @PostMapping(value="/user-register")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // MODEL BOOKING

    /**@PostMapping("/bookings")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Booking newBooking = bookingService.addBooking(booking, userId);
            return ResponseEntity.ok().body(newBooking);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }**/

    @PostMapping("/bookings")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Booking newBooking = bookingService.addBooking(booking, userId);

            // get the user details
            User user = userService.getUser(userId);
            String name = user.getName();
            String phoneNumber = user.getPhoneNumber();
            String date = booking.getDate();
            String time = booking.getTime();
            String restaurant = booking.getResname();


            // prepare the SMS message
            String message = "Dear " + name + ", you have booked a table at  " + restaurant + " in the date " + date + " at " + time + " Thank you for choosing BookIt.";

            // send the SMS message
            smsService.sendSms(phoneNumber, "+17259770639", message);

            return ResponseEntity.ok().body(newBooking);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/userdashboard")
    public String userdashboard(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        List<Booking> bookings = bookingService.getBookingsByUserIdAndStatus(userId, "pending");
        model.addAttribute("bookings", bookings);
        return "userdashboard";
    }

    @PutMapping("/bookings/{id}")
    public String updateBookingStatus(@PathVariable Long id, @RequestBody Map<String, String> request, RedirectAttributes redirectAttributes) {
        String status = request.get("status");
        // Update the booking status in the database
        bookingService.updateBookingStatus(id, status);
        redirectAttributes.addFlashAttribute("message", "Booking status updated successfully!");
        return "admindashboard";
    }

    @GetMapping("/admindashboard")
    public String admindashboard(Model model, HttpSession session) {
        String resname = (String) session.getAttribute("restaurant");
        List<Booking> bookings = null;
        switch (resname) {
            case "Benjarong Thai Restaurant":
                bookings = bookingService.getBookingsByResnameAndStatus("Benjarong Thai Restaurant", "pending");
                break;
            case "Nonya Cafe":
                bookings = bookingService.getBookingsByResnameAndStatus("Nonya Cafe", "pending");
                break;
            case "Cafe 1228":
                bookings = bookingService.getBookingsByResnameAndStatus("Cafe 1228", "pending");
                break;
            case "Cosmic Poblacion":
                bookings = bookingService.getBookingsByResnameAndStatus("Cosmic Poblacion", "pending");
                break;
            case "Firefly Roofdeck Restaurant":
                bookings = bookingService.getBookingsByResnameAndStatus("Firefly Roofdeck Restaurant", "pending");
                break;
            default:
                break;
        }
        model.addAttribute("bookings", bookings);
        return "adminDashboard";
    }

    @GetMapping("/admindashboardcomplete")
    public String admindashboardcomplete(Model model, HttpSession session) {
        String resname = (String) session.getAttribute("restaurant");
        List<Booking> bookings = null;
        switch (resname) {
            case "Benjarong Thai Restaurant":
                bookings = bookingService.getBookingsByResnameAndStatus("Benjarong Thai Restaurant", "completed");
                break;
            case "Nonya Cafe":
                bookings = bookingService.getBookingsByResnameAndStatus("Nonya Cafe", "completed");
                break;
            case "Cafe 1228":
                bookings = bookingService.getBookingsByResnameAndStatus("Cafe 1228", "completed");
                break;
            case "Cosmic Poblacion":
                bookings = bookingService.getBookingsByResnameAndStatus("Cosmic Poblacion", "completed");
                break;
            case "Firefly Roofdeck Restaurant":
                bookings = bookingService.getBookingsByResnameAndStatus("Firefly Roofdeck Restaurant", "completed");
                break;
            default:
                break;
        }
        model.addAttribute("bookings", bookings);
        return "dashComplete";
    }

    @GetMapping("/admindashboardcancel")
    public String admindashboardcance(Model model, HttpSession session) {
        String resname = (String) session.getAttribute("restaurant");
        List<Booking> bookings = null;
        switch (resname) {
            case "Benjarong Thai Restaurant":
                bookings = bookingService.getBookingsByResnameAndStatus("Benjarong Thai Restaurant", "cancelled");
                break;
            case "Nonya Cafe":
                bookings = bookingService.getBookingsByResnameAndStatus("Nonya Cafe", "cancelled");
                break;
            case "Cafe 1228":
                bookings = bookingService.getBookingsByResnameAndStatus("Cafe 1228", "cancelled");
                break;
            case "Cosmic Poblacion":
                bookings = bookingService.getBookingsByResnameAndStatus("Cosmic Poblacion", "cancelled");
                break;
            case "Firefly Roofdeck Restaurant":
                bookings = bookingService.getBookingsByResnameAndStatus("Firefly Roofdeck Restaurant", "cancelled");
                break;
            default:
                break;
        }
        model.addAttribute("bookings", bookings);
        return "dashCancel";
    }


    @PostMapping("/cancelbooking")
    public String cancelBooking(@RequestParam Long bookingId, @RequestParam String reason) {
        Booking booking = bookingService.getBookingById(bookingId);
        booking.setStatus("cancelled");
        booking.setCanreason(reason);
        bookingService.updateBooking(bookingId, booking);
        return "redirect:/userdashboard";
    }

    // MODEL Staff

    @PostMapping(value="/add-staff")
    public ResponseEntity<Void> addStaff(@RequestBody Staff staff) {
        staffService.addStaff(staff);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/restauBasics")
    public String restaurantbasics(Model model) {
        List<Staff> staffList = staffService.findAll();
        model.addAttribute("staffList", staffList);
        return "restauBasics";
    }

    @GetMapping("/deleteStaff/{id}")
    public String deleteStaff(@PathVariable Long id) {
        staffService.delete(id);
        return "restauBasics";
    }

}
