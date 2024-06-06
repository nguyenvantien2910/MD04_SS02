package com.ra.session02.service.impl;

import com.ra.session02.model.entity.Employee;
import com.ra.session02.repository.EmployeeRepository;
import com.ra.session02.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAllByName(String name) {

        return employeeRepository.findEmployeeByFullname(name);
    }

    @Override
    public Employee findById(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(() ->new NoSuchElementException("Employee not found"));
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        employeeRepository.findById(employee.getEmpId()).orElseThrow(() ->new NoSuchElementException("Employee not found"));
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long empId) {
        employeeRepository.deleteById(empId);
    }
}
