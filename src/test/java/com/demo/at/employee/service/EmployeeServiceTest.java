package com.demo.at.employee.service;

import com.demo.at.employee.model.Employee;
import com.demo.at.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private final Employee employeeObj1 = new Employee()
            .setUserId("00001")
            .setFirstName("test-user")
            .setLastName("lastname")
            .setEffectiveDate(new Date())
            .setRole("Dev");
    private final Employee employeeObj2 = new Employee()
            .setUserId("00002")
            .setFirstName("mock-user")
            .setLastName("lastname")
            .setEffectiveDate(new Date())
            .setRole("SA");

    @Test
    void retrieveAllEmployee_success() {
        var response1 = employeeService.retrieveAllEmployee();
        assertEquals(0, response1.size());

        var employeeList = new ArrayList<Employee>();
        employeeList.add(employeeObj1);
        employeeList.add(employeeObj2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        var response2 = employeeService.retrieveAllEmployee();
        assertEquals(2, response2.size());
        assertEquals(employeeList, response2);
    }

    @Test
    void retrieveEmployee_success() {
        var response1 = employeeService.retrieveEmployee("00001");
        assertNull(response1);

        when(employeeRepository.findById(anyString())).thenReturn(java.util.Optional.of(employeeObj1));
        var response = employeeService.retrieveEmployee("00001");
        assertEquals(employeeObj1, response);
    }

    @Test
    void saveEmployee_success() {
        var response1 = employeeService.retrieveEmployee("00003");
        assertNull(response1);

        when(employeeRepository.save(any())).thenReturn(employeeObj2.setUserId("00003"));
        var response = employeeService.saveEmployee(employeeObj2.setUserId("00003"));
        assertEquals("00003", response.getUserId());
        assertEquals(employeeObj2.getFirstName(), response.getFirstName());
    }

    @Test
    void modifyEmployee_success() {
        assertEquals("00001", employeeObj1.getUserId());
        assertEquals("Dev", employeeObj1.getRole());

        when(employeeRepository.findById(anyString())).thenReturn(java.util.Optional.of(employeeObj1));
        when(employeeRepository.save(any())).thenReturn(employeeObj1.setRole("Tester"));
        var response = employeeService.modifyEmployee(employeeObj1.setRole("Tester"));
        assertEquals("00001", response.getUserId());
        assertEquals("Tester", response.getRole());
        assertEquals(employeeObj1.getFirstName(), response.getFirstName());
    }

    @Test
    void deleteEmployee_success() {
        employeeService.deleteEmployee("00001");
        var result = employeeService.retrieveAllEmployee();
        assertFalse(result.stream().anyMatch(obj -> obj.getUserId().equals("00001")));
    }
}
