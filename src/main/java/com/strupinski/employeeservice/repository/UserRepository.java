package com.strupinski.employeeservice.repository;

import com.strupinski.employeeservice.entity.Employee;
import com.strupinski.employeeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<Employee, Long> {
    User getByLogin(String name);

    List<User> getAll();
}
