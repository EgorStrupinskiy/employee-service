package com.strupinski.employeeservice.repository;

import com.strupinski.employeeservice.entity.Authority;
import com.strupinski.employeeservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
