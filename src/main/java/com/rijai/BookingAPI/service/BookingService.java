package com.rijai.BookingAPI.service;

import com.rijai.BookingAPI.model.Booking;
import com.rijai.BookingAPI.model.User;
import com.rijai.BookingAPI.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService{

    @Autowired
    private BookingRepository repository;

    @Override
    public Booking save(Booking booking) {
        return repository.save(booking);
    }

    @Override
    public Booking addBooking(Booking booking, Long userId) {
        User user = new User();
        user.setId(userId);
        booking.setUser(user);
        return repository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUserIdAndStatus(Long userId, String status) {
        return repository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public List<Booking> getBookingsByResnameAndStatus(String resname, String status) {
        return repository.findByResnameAndStatus(resname, status);
    }

    @Override
    public Booking getBookingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateBooking(Long id, Booking updatedBooking) {
        Optional<Booking> optionalBooking = repository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();
            existingBooking.setResname(updatedBooking.getResname());
            existingBooking.setDate(updatedBooking.getDate());
            existingBooking.setTime(updatedBooking.getTime());
            existingBooking.setGuestcount(updatedBooking.getGuestcount());
            existingBooking.setReservationtype(updatedBooking.getReservationtype());
            existingBooking.setStatus(updatedBooking.getStatus());
            existingBooking.setCanreason(updatedBooking.getCanreason());
            existingBooking.setVouchercode(updatedBooking.getVouchercode());
            repository.save(existingBooking);
        } else {
            throw new RuntimeException("Booking not found for id: " + id);
        }
    }

    @Override
    public void updateBookingStatus(Long bookingId, String newStatus) {
        Optional<Booking> optionalBooking = repository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus(newStatus);
            repository.save(booking);
        }
    }

}

