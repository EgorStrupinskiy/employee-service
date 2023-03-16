package com.strupinski.employeeservice.dto.converter;

import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.exception.NoSuchRecordException;
import com.strupinski.employeeservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                .salary(employeeDTO.getSalary())
                .build();

        Optional.ofNullable(employeeDTO.getDepartmentId())
                .ifPresent(id -> {
                    Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                            .orElseThrow(() -> new NoSuchRecordException
                                    (String.format("Department with id=%s not found", employeeDTO.getDepartmentId()))
                            );
                    employee.setDepartment(department);
                });

        return employee;
    }

    public void updateEmployeeFields(EmployeeDTO employeeDTO, Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setSalary(employeeDTO.getSalary());
        if (employeeDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new NoSuchRecordException
                            (String.format("Department with id=%s not found", employeeDTO.getDepartmentId()))
                    );
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
                .salary(employee.getSalary())
                .build();
        System.out.println(employeeDTO);
        Optional.ofNullable(employee.getDepartment())
                .ifPresent(department -> employeeDTO.setDepartmentId(department.getId()));

        return employeeDTO;
    }
}
