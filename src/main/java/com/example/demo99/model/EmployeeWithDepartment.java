package com.example.demo99.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class EmployeeWithDepartment {

    private String _id;
    private String firstname;
    private String lastname;
    private String deptname;
    private Department departmentData;
}
