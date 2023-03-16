package com.strupinski.employeeservice.service.impl;



import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.dto.converter.EmployeeConverter;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.repository.EmployeeRepository;
import com.strupinski.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter converter;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeConverter converter) {
        this.employeeRepository = employeeRepository;
        this.converter = converter;
    }

    @Transactional
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employee) {
        return converter.toDTO(employeeRepository.save(converter.toEntity(employee)));
    }

    @Transactional
    @Override
    public EmployeeDTO getEmployee(Long id) {
        return converter.toDTO(employeeRepository.getById(id));
    }

    @Transactional
    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
