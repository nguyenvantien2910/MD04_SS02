package com.ra.session02.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    @Column(unique = true, nullable = false,length = 50)
    @NotEmpty
    private String fullname;
    private Boolean gender;
    private Date birthday;
    @Column(length = 100)
    private String address;
    @Column(length = 100)
    private String company;
    @Column(length = 50)
    private String department;
    @Min(value = 0)
    private Double salary;
}
