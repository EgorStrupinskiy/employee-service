package com.strupinski.employeeservice.repository;

import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department getByName(String name);

}
