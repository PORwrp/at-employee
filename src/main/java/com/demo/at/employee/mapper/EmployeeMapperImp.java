package com.demo.at.employee.mapper;

import com.demo.at.employee.model.Employee;

public abstract class EmployeeMapperImp implements EmployeeMapper {

    @Override
    public Employee mappingEmployee(Employee oldEmp, Employee newEmp) {
        if (newEmp == null || newEmp.getUserId() == null) {
            return null;
        }

        if (newEmp.getFirstName() != null) {
            oldEmp.setFirstName(newEmp.getFirstName());
        }
        if (newEmp.getLastName() != null) {
            oldEmp.setLastName(newEmp.getLastName());
        }
        if (newEmp.getAddress() != null) {
            oldEmp.setAddress(newEmp.getAddress());
        }
        if (newEmp.getEffectiveDate() != null) {
            oldEmp.setEffectiveDate(newEmp.getEffectiveDate());
        }
        if (newEmp.getRole() != null) {
            oldEmp.setRole(newEmp.getRole());
        }

        return oldEmp;
    }

}
