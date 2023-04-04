package com.example.sample.department.controller;

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
public class DepartmentResource extends EntityModel<Department> {

    private Employee manager;

    public DepartmentResource(Department department, Link... links) {
        super(department, Arrays.asList(links));
        add(linkTo(DepartmentFindController.class).slash(department.getDepartmentId()).withSelfRel());
    }

    public DepartmentResource(Department department, Employee employee, Link... links) {
        super(department, Arrays.asList(links));
        this.manager = employee;
        add(linkTo(DepartmentFindController.class).slash(department.getDepartmentId()).withSelfRel());
    }

}
