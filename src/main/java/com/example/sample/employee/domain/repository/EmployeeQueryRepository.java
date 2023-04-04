package com.example.sample.employee.domain.repository;

import com.example.sample.employee.domain.entity.Employee;
import com.example.sample.employee.query.dto.EmployeeQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeQueryRepository {

    Page<Employee> query(Pageable pageable, EmployeeQueryDto employeeQueryDto);

}
