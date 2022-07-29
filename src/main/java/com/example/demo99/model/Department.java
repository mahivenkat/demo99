package com.example.demo99.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Department {
    @Id
    public String _id;
    public String dept;
}
