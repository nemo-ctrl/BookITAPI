package com.rijai.BookingAPI.repository;

import com.rijai.BookingAPI.model.Admin;
import com.rijai.BookingAPI.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository <Admin, Long> {
    Admin findByUsernameAndPassword(String username, String password);

    Admin findByRestaurant(String restaurant);

    Admin findByUsername(String username);
}