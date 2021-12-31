package com.demo.at.employee.controller;

import com.demo.at.employee.model.Employee;
import com.demo.at.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> retrieveAllEmployee() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.retrieveAllEmployee());
    }

    @GetMapping("/employee/{userId}")
    public ResponseEntity<Employee> retrieveEmployee(@PathVariable(value = "userId") @Valid String userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.retrieveEmployee(userId));
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.saveEmployee(employee));
    }

    @PatchMapping("/employee")
    public ResponseEntity<Employee> modifyEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.modifyEmployee(employee));
    }

    @DeleteMapping("/employee/{userId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "userId") @Valid String userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.deleteEmployee(userId));
    }

}
