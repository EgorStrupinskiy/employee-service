package com.strupinski.employeeservice.controller;

import com.strupinski.employeeservice.dto.UserDTO;
import com.strupinski.employeeservice.model.RegistrationRequest;
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
    public @ResponseBody List<UserDTO> findAllUsers() {
        return this.service.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        service.deleteById(id);

        return "Department with id " + id + " was deleted";
    }

    @PostMapping("/register/")
    public UserDTO addNewUser(@RequestBody RegistrationRequest request) {
        return service.addUser(request.toDTO());
    }
}
