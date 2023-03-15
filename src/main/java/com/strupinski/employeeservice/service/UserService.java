package com.strupinski.employeeservice.service;


import com.strupinski.employeeservice.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username);

    List<User> getAll();
}
