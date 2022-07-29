package com.example.demo99.service;

import com.example.demo99.model.Employee;
import com.example.demo99.model.EmployeeDepartment;
import com.example.demo99.model.EmployeeWithDepartment;
import com.example.demo99.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Employee createEmployee(Employee employee){
    return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(String id, Employee employee){
        Optional<Employee> employeeData = employeeRepository.findById(id);
        if(employeeData.isPresent()){
            Employee _employee = employeeData.get();
            _employee.set_id(employee.get_id());
            _employee.setFirstname(employee.getFirstname());
            _employee.setLastname(employee.getLastname());
            _employee.setDeptname(employee.getDeptname());
            return employeeRepository.save(_employee);
        }else{
            return employeeRepository.save(employee);
        }
    }

    public ResponseEntity<Object> deleteEmployee(String id){
        Optional<Employee> employeeData = employeeRepository.findById(id);
        if(employeeData.isPresent()){
           employeeRepository.delete(employeeData.get());
           return new ResponseEntity<>("Employee is deleted successfully", HttpStatus.OK);
        }else{
            System.out.println("Employee is not found with id " + id);
            return new ResponseEntity<>("Employee is not found: " + id, HttpStatus.OK);
        }

    }

    public List<EmployeeDepartment> getJoinDataFromEmpAndDept(){
        LookupOperation lookupOperation = LookupOperation.newLookup().from("department").localField("deptname").foreignField("_id").as("departmentData");
        ProjectionOperation projectionOperation = Aggregation.project("$_id", "$firstname", "$lastname","$deptname", "$departmentData");
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, projectionOperation);
        List<EmployeeDepartment> results = mongoTemplate.aggregate(aggregation, "employee", EmployeeDepartment.class).getMappedResults();
        return  results;
    }

    public List<EmployeeWithDepartment> getUnwindDataFromEmpAndDept(){
        LookupOperation lookupOperation = LookupOperation.newLookup().from("department").localField("deptname").foreignField("_id").as("departmentData");
        AggregationOperation unwind = Aggregation.unwind("departmentData");
        ProjectionOperation projectionOperation = Aggregation.project("$_id", "$firstname", "$lastname", "$deptname", "$departmentData");
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, unwind, projectionOperation);
        List<EmployeeWithDepartment> results = mongoTemplate.aggregate(aggregation, "employee", EmployeeWithDepartment.class).getMappedResults();
        return  results;
    }
}
