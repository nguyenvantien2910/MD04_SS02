package com.ra.session02.rest_controller;

import com.ra.session02.model.dto.request.EmployeeRequest;
import com.ra.session02.model.entity.Employee;
import com.ra.session02.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class RestEmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId) {
        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long empId, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteById(empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/searchAndPagging")
    public ResponseEntity<List<Employee>> searchbyNameAndPagging(@RequestBody EmployeeRequest employeeRequest) {
        List<Employee> employees = employeeService.findAllPageable(employeeRequest.getFullname(), employeeRequest.getPage() - 1, employeeRequest.getPerPage(), employeeRequest.getSortBy(), employeeRequest.getDirection()).getContent();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/searchAndPaggingOrderBySalary")
    public ResponseEntity<List<Employee>> searchbySalary(@RequestBody EmployeeRequest employeeRequest) {
        List<Employee> employees = employeeService.findAllPageableAndSortBySalaryDesc(employeeRequest.getFullname(), employeeRequest.getPage() - 1, employeeRequest.getPerPage(), employeeRequest.getSortBy(), employeeRequest.getDirection());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/searchByRangeSalary/{min}&{max}")
    public ResponseEntity<List<Employee>> searchByRangeSalary(@PathVariable Double min, @PathVariable Double max) {
        List<Employee> employees = employeeService.findByRangeSalary(min.longValue(), max.longValue());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}
