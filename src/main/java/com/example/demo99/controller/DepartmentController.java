package com.example.demo99.controller;

import com.example.demo99.model.Department;
import com.example.demo99.model.Employee;
import com.example.demo99.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    @GetMapping("/allDepartments")
    public List<Department> getAllDepartments(){
        return  departmentService.getAllDepartments();
    }
}
