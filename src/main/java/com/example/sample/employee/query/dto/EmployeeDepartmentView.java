package com.example.sample.employee.query.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class EmployeeDepartmentView {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate hireDate;

    private BigDecimal salary;

    private BigDecimal commissionPct;

    private String departmentName;

    private String city;

    private String countryName;

    private String regionName;

}
