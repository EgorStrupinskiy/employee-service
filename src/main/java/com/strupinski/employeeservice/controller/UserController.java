package com.strupinski.employeeservice.controller;

import com.strupinski.employeeservice.dto.DepartmentDTO;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.entity.User;
import com.strupinski.employeeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAll() {
        return this.service.getAll();
    }

    @PostMapping("/")
    public void addNewUser(@RequestBody User user) {
        service.addUser(user);
    }
}
