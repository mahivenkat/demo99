package com.example.demo99.controller;

import com.example.demo99.model.Employee;
import com.example.demo99.model.EmployeeDepartment;
import com.example.demo99.model.EmployeeWithDepartment;
import com.example.demo99.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee){
        return  employeeService.createEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PutMapping("updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee){
        return  employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") String id){
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/getEmployeeAndDeptDetails")
    public List<EmployeeDepartment> getEmployeeDepartmentDetails(){
        return employeeService.getJoinDataFromEmpAndDept();
    }

    @GetMapping("/getUnWindEmployeeAndDeptDetails")
    public List<EmployeeWithDepartment> getUnWindEmployeeDepartmentDetails(){
        return employeeService.getUnwindDataFromEmpAndDept();
    }

}
