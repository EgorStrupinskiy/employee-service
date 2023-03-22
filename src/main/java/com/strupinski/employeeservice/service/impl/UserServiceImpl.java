package com.strupinski.employeeservice.service.impl;


import com.strupinski.employeeservice.dto.UserDTO;
import com.strupinski.employeeservice.dto.converter.UserConverter;
import com.strupinski.employeeservice.dto.converter.UserMapper;
import com.strupinski.employeeservice.entity.User;
import com.strupinski.employeeservice.repository.UserRepository;
import com.strupinski.employeeservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final UserConverter converter;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", username));
        }
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), true, true, true, true, new HashSet<>());
    }


    @Override
    public UserDTO addUser(UserDTO user) {
        User requestUser = UserMapper.INSTANCE.fromDto(user);
        User addedUser = userRepository.save(requestUser);

        return UserMapper.INSTANCE.toDto(addedUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", username));
        }

        return converter.toDTO(user);
    }

}
