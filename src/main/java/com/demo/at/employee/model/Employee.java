package com.demo.at.employee.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "userid")
    @Size(max = 5)
    private String userId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "effectivedate")
    private Date effectiveDate;

    @Column(name = "role")
    private String role;

}
