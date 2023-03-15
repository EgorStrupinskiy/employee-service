package com.strupinski.employeeservice.controller;

import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RESTController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }


    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getEmployee(id);

        employeeService.deleteEmployee(id);

        return "Employee with id " + id + " was deleted";
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployee(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        return employee;
    }
}
