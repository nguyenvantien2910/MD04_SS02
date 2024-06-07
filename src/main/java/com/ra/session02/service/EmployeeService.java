package com.ra.session02.service;

import com.ra.session02.model.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long empId);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void deleteById(Long empId);
    Page<Employee> findAllPageable(String searchName,Integer page,Integer perpage,String orderBy,String direction);
    List<Employee> saveAll(List<Employee> employees);
    List<Employee> findByRangeSalary(Long min, Long max);
    List<Employee> findTop10BySalary();
    List<Employee>findAllPageableAndSortBySalaryDesc(String searchName,Integer page,Integer perpage,String orderBy,String direction);
}
