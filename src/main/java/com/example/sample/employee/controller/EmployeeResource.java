package com.example.sample.employee.controller;

import com.example.sample.department.domain.entity.Department;
import com.example.sample.employee.domain.entity.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class EmployeeResource extends EntityModel<Employee> {

    private Department department;

    public EmployeeResource(Employee employee, Link... links) {
        super(employee, Arrays.asList(links));
        add(linkTo(EmployeeFindController.class).slash(employee.getEmployeeId()).withSelfRel());
    }

    public EmployeeResource(Employee employee, Department department, Link... links) {
        super(employee, Arrays.asList(links));
        this.department = department;
        add(linkTo(EmployeeFindController.class).slash(employee.getEmployeeId()).withSelfRel());
    }

}
