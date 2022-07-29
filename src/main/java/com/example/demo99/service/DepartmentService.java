package com.example.demo99.service;

import com.example.demo99.model.Department;
import com.example.demo99.model.Employee;
import com.example.demo99.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentService {
    private  final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
}
