package com.example.sample.employee.controller;

import com.example.sample.department.domain.entity.Department;
import com.example.sample.department.domain.repository.DepartmentRepository;
import com.example.sample.employee.domain.entity.Employee;
import com.example.sample.employee.domain.repository.EmployeeRepository;
import com.example.sample.employee.query.dao.EmployeeQueryDao;
import com.example.sample.employee.query.dto.EmployeeQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/employee")
@RestController
public class EmployeeFindController {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    private final EmployeeQueryDao employeeQueryDao;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        WebMvcLinkBuilder slash = linkTo(EmployeeFindController.class).slash(employee.getEmployeeId());

        EmployeeResource employeeResource = new EmployeeResource(employee);
        employeeResource.add(Link.of("/docs/index.html#resources-find-employee", "profile"));
        employeeResource.add(linkTo(EmployeeFindController.class).withRel("query"));

        return ResponseEntity.ok().body(employeeResource);
    }

    @GetMapping
    public ResponseEntity<?> query(@PageableDefault(sort = "employeeId", direction = Sort.Direction.ASC) Pageable pageable, EmployeeQueryDto employeeQueryDto) {
        Page<Employee> all = employeeRepository.query(pageable, employeeQueryDto);
        return ResponseEntity.ok().body(all);
    }


    @GetMapping("/{id}/department")
    public ResponseEntity<?> findDepartment(@PathVariable Long id) {
        /*
         * 1. mybatis
         * 2. jpa 동적 인스턴스
         * 3. Resource 활용
         * */
//        EmployeeDepartmentView employeeDepartmentView = employeeQueryDao.find(id);
        Employee employee = employeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Department department = departmentRepository.find(employee.getDepartmentId()).orElseThrow(IllegalArgumentException::new);

        EmployeeResource employeeResource = new EmployeeResource(employee, department);
        employeeResource.add(Link.of("/docs/index/html#resources-find-employee-with-department", "profile"));
        employeeResource.add(linkTo(EmployeeFindController.class).withRel("query"));

        return ResponseEntity.ok().body(employeeResource);
    }

}
