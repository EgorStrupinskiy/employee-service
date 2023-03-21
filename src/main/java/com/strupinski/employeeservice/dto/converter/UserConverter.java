package com.strupinski.employeeservice.dto.converter;

import com.strupinski.employeeservice.dto.DepartmentDTO;
import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.dto.UserDTO;
import com.strupinski.employeeservice.entity.Authority;
import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.entity.User;
import com.strupinski.employeeservice.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public UserConverter(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public User toEntity(UserDTO userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();

        Optional.ofNullable(userDTO.getAuthority())
                .ifPresent(id -> {
                    Authority au = authorityRepository.findByName(userDTO.getAuthority());
                    user.setAuthority(au);
                });

        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        System.out.println(userDTO);
        Optional.ofNullable(user.getAuthority())
                .ifPresent(authority -> userDTO.setAuthority(authority.getName()));

        return userDTO;
    }
}
