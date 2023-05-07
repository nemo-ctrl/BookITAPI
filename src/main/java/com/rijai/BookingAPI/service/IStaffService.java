package com.rijai.BookingAPI.service;

import com.rijai.BookingAPI.model.Staff;

import java.util.List;

public interface IStaffService {
    Staff addStaff(Staff staff);

    List<Staff> findAll();

    void delete(Long id);
}
