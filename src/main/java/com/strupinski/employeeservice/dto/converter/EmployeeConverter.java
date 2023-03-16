package com.strupinski.employeeservice.dto.converter;

import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EmployeeConverter {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeConverter(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .id(employeeDTO.getId())
                .name(employeeDTO.getName())
                .surname(employeeDTO.getSurname())
                .build();

        Optional.of(employeeDTO.getDepartmentId())
                .ifPresent(id -> {
                    Department department = departmentRepository.getById(employeeDTO.getDepartmentId());
                    employee.setDepartment(department);
                });

        return employee;
    }

    public void updateEmployeeFields(EmployeeDTO employeeDTO, Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());

        if (employeeDTO.getDepartmentId() != null) {
            Department department = departmentRepository.getById(employeeDTO.getDepartmentId());
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }
    }

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .build();

        Optional.ofNullable(employee.getDepartment())
                .ifPresent(department -> employeeDTO.setDepartmentId(department.getId()));

        return employeeDTO;
    }
}
