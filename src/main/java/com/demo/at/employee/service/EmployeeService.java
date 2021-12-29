package com.demo.at.employee.service;


import com.demo.at.employee.mapper.EmployeeMapper;
import com.demo.at.employee.model.Employee;
import com.demo.at.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> retrieveAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee retrieveEmployee(String userId) {
        return employeeRepository.findById(userId).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        var employeeInfo = retrieveEmployee(employee.getUserId());
        if (Objects.isNull(employeeInfo)) {
            return employeeRepository.save(employee);
        }
        return null;
    }

    public Employee modifyEmployee(Employee employee) {
        var employeeInfo = retrieveEmployee(employee.getUserId());
        if (Objects.nonNull(employeeInfo)) {
            var patchEmployee = EmployeeMapper.INSTANCE.mappingEmployee(employeeInfo, employee);
            return employeeRepository.save(patchEmployee);
        } else {
            return null;
        }
    }

    public String deleteEmployee(String userId) {
        try {
            var employeeInfo = retrieveEmployee(userId);
            if (Objects.nonNull(employeeInfo)) {
                employeeRepository.delete(new Employee().setUserId(userId));
                return "Delete employee userId: " + userId + " successfully";
            } else {
                return "Employee userId: " + userId + " is not found";
            }
        } catch (Exception ex) {
            return "Cannot delete employee userId: " + userId;
        }
    }
}