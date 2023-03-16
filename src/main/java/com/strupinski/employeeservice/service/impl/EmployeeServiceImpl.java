package com.strupinski.employeeservice.service.impl;



import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.dto.converter.EmployeeConverter;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.exception.NoSuchRecordException;
import com.strupinski.employeeservice.repository.EmployeeRepository;
import com.strupinski.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return converter.toDTO(employeeRepository.save(converter.toEntity(employeeDTO)));
    }

    @Override
    @Transactional
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchRecordException
                        (String.format("Employee with id=%s not found", id))
                );
        return converter.toDTO(employee);    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
