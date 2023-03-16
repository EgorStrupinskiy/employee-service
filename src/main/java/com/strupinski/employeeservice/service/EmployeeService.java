package com.strupinski.employeeservice.service;


import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    EmployeeDTO saveEmployee(EmployeeDTO employee);

    EmployeeDTO getEmployee(Long id);


    void deleteEmployee(Long id);
}
