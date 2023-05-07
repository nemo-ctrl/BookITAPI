package com.rijai.BookingAPI.repository;

import com.rijai.BookingAPI.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}