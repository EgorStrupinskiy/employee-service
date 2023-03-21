package com.strupinski.employeeservice.service.impl;


import com.strupinski.employeeservice.dto.DepartmentDTO;
import com.strupinski.employeeservice.dto.converter.DepartmentConverter;
import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.exception.NoSuchRecordException;
import com.strupinski.employeeservice.repository.DepartmentRepository;
import com.strupinski.employeeservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter converter;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentConverter converter) {
        this.departmentRepository = departmentRepository;
        this.converter = converter;
    }


    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO department) {
        System.out.println("department added");
        return converter.toDTO(departmentRepository.save(converter.toEntity(department)));
    }

    @Transactional
    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchRecordException(String.format("Department with id=%s not found", id))
                );
        return converter.toDTO(department);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new NoSuchRecordException
                    (String.format("Department with id=%s not found for deleting", id));
        }
        departmentRepository.deleteById(id);
    }
}
