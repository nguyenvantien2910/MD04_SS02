package com.ra.session02.repository;

import com.ra.session02.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where e.fullname like concat('%',:fullname,'%') ")
    List<Employee> findEmployeeByFullname(String fullname);
}
