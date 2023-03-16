package com.strupinski.employeeservice.service;


import com.strupinski.employeeservice.dto.DepartmentDTO;
import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO department);

    List<DepartmentDTO> getAll();

    DepartmentDTO findById(Long id);

    void deleteById(Long id);
}
