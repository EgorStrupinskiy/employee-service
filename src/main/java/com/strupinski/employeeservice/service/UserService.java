package com.strupinski.employeeservice.service;


import com.strupinski.employeeservice.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    UserDTO addUser(UserDTO user);

    void deleteById(Long id);
    List<UserDTO> findAll();

    UserDTO findByUsername(String username);
}
