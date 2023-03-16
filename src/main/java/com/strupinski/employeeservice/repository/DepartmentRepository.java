package com.strupinski.employeeservice.repository;

import com.strupinski.employeeservice.dto.DepartmentDTO;
import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
