package com.rijai.BookingAPI.service;

import com.rijai.BookingAPI.model.Booking;

import java.util.List;

public interface IBookingService {
    Booking save(Booking booking);

    Booking addBooking(Booking booking, Long userId);

    List<Booking> getBookingsByUserIdAndStatus(Long userId, String status);

    List<Booking> getBookingsByResnameAndStatus(String resname, String status);

    Booking getBookingById(Long id);

    void updateBooking(Long id, Booking updatedBooking);

    void updateBookingStatus(Long id, String status);


    /**List<Booking> BenjarongCompleted(String resname, String status);

    List<Booking> BenjarongCancelled(String resname, String status);



    List<Booking> BenjarongPending(String resname, String status);

    List<Booking> NonyaPending(String resname, String status);

    List<Booking> NonyaCompleted(String resname, String status);

    List<Booking> NonyaCancelled(String resname, String status);

    List<Booking> Cafe1228Pending(String resname, String status);

    List<Booking> Cafe1228Completed(String resname, String status);

    List<Booking> Cafe1228Cancelled(String resname, String status);

    List<Booking> CosmicPending(String resname, String status);

    List<Booking> CosmicCompleted(String resname, String status);

    List<Booking> CosmicCancelled(String resname, String status);

    List<Booking> FireflyPending(String resname, String status);

    List<Booking> FireflyCompleted(String resname, String status);

    List<Booking> FireflyCancelled(String resname, String status);**/
}
