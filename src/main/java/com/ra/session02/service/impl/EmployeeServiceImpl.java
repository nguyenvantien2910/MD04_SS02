package com.ra.session02.service.impl;

import com.ra.session02.model.entity.Employee;
import com.ra.session02.repository.EmployeeRepository;
import com.ra.session02.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Employee findById(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        employeeRepository.findById(employee.getEmpId()).orElseThrow(() -> new NoSuchElementException("Employee not found"));
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public Page<Employee> findAllPageable(String searchName, Integer page, Integer perpage, String orderBy, String direction) {
        Pageable pageable = null;
        if (orderBy != null && orderBy.isEmpty()) {
            Sort sort = null;
            switch (direction) {
                case "asc":
                    sort = Sort.by(orderBy).ascending();
                    break;
                case "desc":
                    sort = Sort.by(orderBy).descending();
                    break;
            }

            pageable = PageRequest.of(page, perpage, sort);
        } else {
            pageable = PageRequest.of(page, perpage);
        }

        if (searchName != null && !searchName.isEmpty()) {
            return employeeRepository.findEmployeeByFullnameAndSorting(searchName,pageable);
        } else {
            return employeeRepository.findAll(pageable);
        }
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public List<Employee> findByRangeSalary(Long min, Long max) {
        return employeeRepository.findEmployeeBySalaryRange(min,max);
    }

    @Override
    public List<Employee> findTop10BySalary() {
        return employeeRepository.findTop10BySalary();
    }

    @Override
    public List<Employee> findAllPageableAndSortBySalaryDesc(String searchName, Integer page, Integer perpage, String orderBy, String direction) {
        return List.of();
    }
}
