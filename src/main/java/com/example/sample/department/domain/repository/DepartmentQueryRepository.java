package com.example.sample.department.domain.repository;

import com.example.sample.department.domain.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentQueryRepository {

    List<Department> find();

    Optional<Department> find(Long id);

}
