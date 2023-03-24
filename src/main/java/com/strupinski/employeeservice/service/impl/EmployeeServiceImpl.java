package com.strupinski.employeeservice.service.impl;



import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.dto.converter.EmployeeConverter;
import com.strupinski.employeeservice.dto.converter.EmployeeMapper;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.exception.NoSuchRecordException;
import com.strupinski.employeeservice.repository.EmployeeRepository;
import com.strupinski.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter converter;
    private final EmployeeMapper mapper;


    @Transactional
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        var entityDTO = mapper.toEntity(employeeDTO);
        var returnedEntity = employeeRepository.save(entityDTO);
        var convertedEntity = mapper.toDto(returnedEntity);
        return convertedEntity;
//        return converter.toDTO(employeeRepository.save(converter.toEntity(employeeDTO)));
    }

    @Override
    @Transactional
    public EmployeeDTO findById(Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new NoSuchRecordException
//                        (String.format("Employee with id=%s not found", id))
//                );
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchRecordException
                        (String.format("Employee with id=%s not found", id))
                );
        return mapper.toDto(employee);    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
