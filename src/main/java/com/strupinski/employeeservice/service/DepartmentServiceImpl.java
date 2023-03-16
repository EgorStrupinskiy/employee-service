package com.strupinski.employeeservice.service;



import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.User;
import com.strupinski.employeeservice.repository.DepartmentRepository;
import com.strupinski.employeeservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;


    @Transactional
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}
