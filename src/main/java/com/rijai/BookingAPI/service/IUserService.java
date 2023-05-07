package com.rijai.BookingAPI.service;

import com.rijai.BookingAPI.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();
    User findByUsernameAndPassword(String username, String password);
    User addUser(User user);
    User getUser(long id);
    User deleteUser(long id);
    Optional<User> findById(long id);


}
