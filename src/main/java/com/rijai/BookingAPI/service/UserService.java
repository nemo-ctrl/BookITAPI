package com.rijai.BookingAPI.service;

import com.rijai.BookingAPI.model.User;
import com.rijai.BookingAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        User user = repository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUser(long id) {
        Optional optional=repository.findById(id);
        if(optional.isEmpty())
            return null;
        else
            return (User) optional.get();
    }

    @Override
    public User deleteUser(long id)
    {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()) {
            repository.delete(user.get());
        }
        return null;
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }
}
