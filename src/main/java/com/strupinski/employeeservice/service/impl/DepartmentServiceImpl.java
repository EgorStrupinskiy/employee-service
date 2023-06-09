package com.strupinski.employeeservice.service.impl;



import com.strupinski.employeeservice.dto.DepartmentDTO;
import com.strupinski.employeeservice.dto.converter.DepartmentConverter;
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
        return converter.toDTO(departmentRepository.save(converter.toEntity(department)));
    }

    @Transactional
    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }
}
