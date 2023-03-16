package com.strupinski.employeeservice.controller;

import com.strupinski.employeeservice.dto.DepartmentDTO;
import com.strupinski.employeeservice.entity.Department;
import com.strupinski.employeeservice.entity.User;
import com.strupinski.employeeservice.service.DepartmentService;
import com.strupinski.employeeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<DepartmentDTO> getAll() {
        return this.service.getAll();
    }

    @PostMapping("/")
    public DepartmentDTO addNewDepartment(@RequestBody DepartmentDTO department) {
        return service.saveDepartment(department);
    }
}
