package com.example.demo99.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Employee {
    @Id
    private String _id;
    private String firstname;
    private String lastname;
    private String deptname;

}
