package com.example.sample.department.ui;

import com.example.sample.department.domain.entity.Department;
import com.example.sample.department.domain.repository.DepartmentRepository;
import com.example.sample.employee.domain.entity.Employee;
import com.example.sample.employee.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/department")
@RestController
public class DepartmentFindController {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Department department = departmentRepository.find(id).orElseThrow(IllegalArgumentException::new);

        DepartmentResource departmentResource = new DepartmentResource(department);
        departmentResource.add(Link.of("/docs/index.html#resources-find-department", "profile"));

        return ResponseEntity.ok().body(departmentResource);
    }

    @GetMapping("/{departmentId}/manager")
    public ResponseEntity<?> findManager(@PathVariable Long departmentId) {
        Department department = departmentRepository.find(departmentId).orElseThrow(IllegalArgumentException::new);
        Employee employee = employeeRepository.findById(department.getManagerId()).orElseThrow(IllegalArgumentException::new);

        DepartmentResource departmentResource = new DepartmentResource(department, employee);
        departmentResource.add(Link.of("/docs/index.html#resources-find-department-with-manager", "profile"));

        return ResponseEntity.ok().body(departmentResource);
    }

    @GetMapping
    public ResponseEntity<?> find() {
        List<Department> all = departmentRepository.find();
        return ResponseEntity.ok().body(all);
    }

}
