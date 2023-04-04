package com.example.sample.department.domain.repository;

import com.example.sample.department.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>, DepartmentQueryRepository {
}
