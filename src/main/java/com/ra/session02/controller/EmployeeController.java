package com.ra.session02.controller;

import com.ra.session02.model.entity.Employee;
import com.ra.session02.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
        private final EmployeeService employeeService;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/genList")
    public String genList(Model model){
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 55; i++) {
            Employee e = new Employee(0L,"Employee" + i,true,new Date(),"address" + i,"Company" + i,"Department" + i, (double) (15000000+i));
            list.add(e);
        }
        List<Employee> employees = employeeService.saveAll(list);
        model.addAttribute("success","Save all Employee successfully!");
        return "index";
    }

    @GetMapping("/listEmployee")
    public String listEmployee(@RequestParam(defaultValue = "")String name,
                              @RequestParam(defaultValue = "1")Integer page,
                              @RequestParam(defaultValue = "8")Integer perpage,
                              @RequestParam(defaultValue = "fullName")String sortBy,
                              @RequestParam(defaultValue = "DESC")String direction,
                              Model model){
        Page<Employee> employeePaging = employeeService.findAllPageable(name, page - 1, perpage, sortBy, direction);
        model.addAttribute("list",employeePaging.getContent());
        model.addAttribute("page",page);
        model.addAttribute("name",name);
        model.addAttribute("sortBy",sortBy);
        model.addAttribute("direction",direction);
        model.addAttribute("listPage",employeePaging.getTotalPages());
        return "listEmployee";
    }
}
