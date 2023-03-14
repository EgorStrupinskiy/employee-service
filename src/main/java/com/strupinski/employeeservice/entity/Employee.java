package com.strupinski.employeeservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;



@Entity
@Table(name = "employees")
@Data
public class Employee {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private int salary;

}
