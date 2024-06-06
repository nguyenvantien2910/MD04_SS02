package com.ra.session02.service;

import com.ra.session02.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    List<Employee> findAllByName(String name);
    Employee findById(Long empId);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void deleteById(Long empId);
}
