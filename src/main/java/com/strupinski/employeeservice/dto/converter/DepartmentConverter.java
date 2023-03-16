package com.strupinski.employeeservice.dto.converter;

import com.strupinski.employeeservice.dto.DepartmentDTO;
import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.exception.NoSuchRecordException;
import com.strupinski.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepartmentConverter {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentConverter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Department toEntity(DepartmentDTO departmentDTO) {
        Department department = Department.builder()
                .name(departmentDTO.getName())
                .id(departmentDTO.getId())
                .build();

        Optional.ofNullable(departmentDTO.getEmployeeIds())
                .ifPresent(employeeIds -> employeeIds.forEach(id -> {
                    department.addEmployee(employeeRepository.findById(id)
                            .orElseThrow(() -> new NoSuchRecordException
                                    (String.format("Employee with id=%s not found", id)))
                    );
                }));

        return department;
    }

    public DepartmentDTO toDTO(Department department) {
        DepartmentDTO departmentDTO = DepartmentDTO.builder()
                .name(department.getName())
                .id(department.getId())
                .build();

        Optional.ofNullable(department.getEmployees())
                .ifPresent(employees -> employees.forEach(employee -> departmentDTO.addEmployeeId(employee.getId())));

        return departmentDTO;
    }
}
