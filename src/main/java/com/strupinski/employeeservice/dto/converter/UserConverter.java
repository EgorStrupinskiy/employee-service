package com.strupinski.employeeservice.dto.converter;

import com.strupinski.employeeservice.dto.UserDTO;
import com.strupinski.employeeservice.entity.Authority;
import com.strupinski.employeeservice.entity.User;
import com.strupinski.employeeservice.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final AuthorityRepository authorityRepository;

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
        System.out.println("UserDTO converted to entity");

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
