package com.rijai.BookingAPI.service;

import com.rijai.BookingAPI.model.Staff;
import com.rijai.BookingAPI.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService implements IStaffService{

    @Autowired
    private StaffRepository repository;
    @Override
    public Staff addStaff(Staff staff) {
        return repository.save(staff);
    }

    @Override
    public List<Staff> findAll() {
        return (List<Staff>) repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
