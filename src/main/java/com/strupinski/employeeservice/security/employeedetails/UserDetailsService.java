package com.strupinski.employeeservice.security.employeedetails;

import com.strupinski.employeeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDetails userDetails;
    private final UserService userService;

    @Autowired
    public UserDetailsService(UserDetails userDetails, UserService userService) {
        this.userDetails = userDetails;
        this.userService = userService;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetails.toUserDetails(userService.findByUsername(username));
    }
}
