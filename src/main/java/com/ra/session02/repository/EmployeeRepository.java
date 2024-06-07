package com.ra.session02.repository;

import com.ra.session02.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long> {
    @Query("select e from Employee e where e.fullname like concat('%',:fullname,'%') ")
    Page<Employee> findEmployeeByFullnameAndSorting(String fullname, Pageable pageable);
    @Query("select e from Employee e where e.salary between :min and :max")
    List<Employee> findEmployeeBySalaryRange(Long min, Long max);
    @Query("select e from Employee e order by e.salary desc limit 10")
    List<Employee> findTop10BySalary();
    @Query("select e from Employee e where e.fullname like concat('%',:fullname,'%') order by e.salary desc ")
    List<Employee>findAllPageableAndSortBySalaryDesc(String searchName,Integer page,Integer perpage,String orderBy,String direction);
}
