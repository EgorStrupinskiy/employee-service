package com.strupinski.employeeservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    @Column(name = "username")

    private String login;
    @Column(name = "password")

    private String password;
}
