package com.rijai.BookingAPI.service;

import com.rijai.BookingAPI.model.Admin;
import com.rijai.BookingAPI.model.User;
import com.rijai.BookingAPI.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService{

    @Autowired
    private AdminRepository repository;

    @Override
    public List<Admin> findAll() {
        return (List<Admin>) repository.findAll();
    }

    @Override
    public Admin findByRestaurant(String restaurant){
        return repository.findByRestaurant(restaurant);
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        Admin admin = repository.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    @Override
    public Admin loginAdmin(String username, String password){
        Admin admin = repository.findByUsernameAndPassword(username, password);
        return admin;
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public Admin getAdmin(long id) {
        Optional optional=repository.findById(id);
        if(optional.isEmpty())
            return null;
        else
            return (Admin) optional.get();
    }

    @Override
    public Admin deleteAdmin(long id)
    {
        Optional<Admin> admin = repository.findById(id);
        if(admin.isPresent()) {
            repository.delete(admin.get());
        }
        return null;
    }
}
