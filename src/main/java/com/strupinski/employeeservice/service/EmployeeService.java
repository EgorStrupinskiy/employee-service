package com.strupinski.employeeservice.service;


import com.strupinski.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);


    Employee getEmployee(int id);

    void deleteEmployee(int id);
}
