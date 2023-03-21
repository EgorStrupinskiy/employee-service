package com.strupinski.employeeservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "authority")
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authority")

    private String name;

    @OneToMany(mappedBy = "authority")
    private List<User> users;
}
