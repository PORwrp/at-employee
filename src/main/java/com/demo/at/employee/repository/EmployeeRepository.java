package com.demo.at.employee.repository;

import com.demo.at.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:firstname%")
    List<Employee> findByFirstName(String firstname);

}
