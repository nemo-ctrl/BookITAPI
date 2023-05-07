package com.rijai.BookingAPI.service;

import com.rijai.BookingAPI.model.Admin;

import java.util.List;

public interface IAdminService {

    List<Admin> findAll();

    Admin findByRestaurant(String restaurant);

    Admin loginAdmin(String username, String password);
    Admin addAdmin(Admin admin);

    Admin findByUsernameAndPassword(String username, String password);

    Admin getAdmin(long id);
    Admin deleteAdmin(long id);
}
