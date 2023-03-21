package com.strupinski.employeeservice.model;

import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.dto.UserDTO;
import com.strupinski.employeeservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {

    private String username;

    private String password;

    private String authority;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .username(username)
                .password(password)
                .authority(authority)
                .build();
    }
}