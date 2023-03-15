package com.strupinski.employeeservice.service;



import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Optional<Employee> getEmployee(int id) {
        return employeeRepository.findById((long) id);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById((long) id);
    }
}
