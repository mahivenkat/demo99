package com.example.demo99.repository;

import com.example.demo99.model.Department;
import com.example.demo99.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, String> {
}
