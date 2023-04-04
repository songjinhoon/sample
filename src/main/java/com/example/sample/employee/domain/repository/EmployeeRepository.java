package com.example.sample.employee.domain.repository;

import com.example.sample.employee.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeQueryRepository {

    List<Employee> findByDepartmentId(Long salaryUpdateDto);

}
