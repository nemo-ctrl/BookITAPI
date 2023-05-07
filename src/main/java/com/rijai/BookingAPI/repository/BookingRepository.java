package com.rijai.BookingAPI.repository;

import com.rijai.BookingAPI.model.Booking;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

   // Booking Benjarong(String resname, String status);

   // Booking Nonya(String resname, String status);

    //Booking Cafe1228(String resname, String status);

    //Booking Cosmic(String resname, String status);

    //Booking Firefly(String resname, String status);

    List<Booking> findByUserId(Long userId);

    List<Booking> findByUserIdAndStatus(Long userId, String status);

    List<Booking> findByResnameAndStatus(String resname, String status);

    @Modifying
    @Query("update Booking b set b.status = :status where b.id = :id")
    void updateBookingStatus(@Param("id") Long id, @Param("status") String status);
}