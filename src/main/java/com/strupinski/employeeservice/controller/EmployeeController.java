package com.strupinski.employeeservice.controller;

import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }


    @PostMapping("/")
    public EmployeeDTO addNewEmployee(@RequestBody EmployeeDTO employee) {

        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employee) {
        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployee(id);

        employeeService.deleteEmployee(id);

        return "Employee with id " + id + " was deleted";
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployee(id);
        return employee;
    }
}
