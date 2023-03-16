package com.strupinski.employeeservice.service;


import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO saveEmployee(EmployeeDTO employee);

    EmployeeDTO findById(Long id);


    void deleteById(Long id);
}
