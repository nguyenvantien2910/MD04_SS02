package com.ra.session02.controller;

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
public class EmployeeController {
        private final EmployeeService employeeService;

        @GetMapping
        public ResponseEntity<List<Employee>> getAllEmployees() {
                return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
        }

        @GetMapping("/{empId}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId) {
                return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
                return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
        }

        @PutMapping
        public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
                return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
        }

        @DeleteMapping("/{empId}")
        public ResponseEntity<Employee> deleteEmployee(@PathVariable Long empId) {
                employeeService.deleteById(empId);
                return new ResponseEntity<>(HttpStatus.OK);
        }

        @GetMapping("/search/{fullname}")
        public ResponseEntity<List<Employee>> getEmployeeByFullname(@PathVariable String fullname) {
                return new ResponseEntity<>(employeeService.findAllByName(fullname), HttpStatus.OK);
        }
}
