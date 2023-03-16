package com.strupinski.employeeservice.service;


import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();
}
