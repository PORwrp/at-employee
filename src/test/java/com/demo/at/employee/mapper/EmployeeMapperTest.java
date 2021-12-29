package com.demo.at.employee.mapper;

import com.demo.at.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeMapperTest {

    private final Employee employeeObj1 = new Employee()
            .setUserId("00001")
            .setFirstName("test-user")
            .setLastName("lastname")
            .setEffectiveDate(new Date())
            .setRole("Dev");
    private final Employee employeeObj2 = new Employee()
            .setUserId("00001")
            .setFirstName("mock-new")
            .setRole("SA");

    @Test
    void mappingEmployee() {
        var mergeEmployee = EmployeeMapper.INSTANCE.mappingEmployee(employeeObj1, employeeObj2);
        assertEquals("00001", mergeEmployee.getUserId());
        assertEquals("mock-new", mergeEmployee.getFirstName());
        assertEquals("lastname", mergeEmployee.getLastName());
        assertEquals("SA", mergeEmployee.getRole());
    }
}
