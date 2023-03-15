package com.strupinski.employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private Integer age;
}
