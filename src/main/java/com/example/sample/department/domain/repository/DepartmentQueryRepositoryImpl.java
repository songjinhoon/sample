package com.example.sample.department.domain.repository;

import com.example.sample.department.domain.entity.Department;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.sample.department.domain.entity.QDepartment.department;
import static com.example.sample.department.domain.entity.detail.QCountry.country;
import static com.example.sample.department.domain.entity.detail.QLocation.location;
import static com.example.sample.department.domain.entity.detail.QRegion.region;
import static com.example.sample.employee.domain.entity.QEmployee.employee;

public class DepartmentQueryRepositoryImpl implements DepartmentQueryRepository {

    private JPAQueryFactory jpaQueryFactory;

    public DepartmentQueryRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Department> find() {
        return jpaQueryFactory.selectFrom(department)
                .leftJoin(employee).on(employee.employeeId.eq(department.managerId)).fetchJoin()
                .innerJoin(department.location, location).fetchJoin()
                .innerJoin(location.country, country).fetchJoin()
                .innerJoin(country.region, region).fetchJoin()
                .orderBy(department.departmentId.asc())
                .fetch()
                ;
    }

    @Override
    public Optional<Department> find(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(department)
                .leftJoin(employee).on(employee.employeeId.eq(department.managerId)).fetchJoin()
                .innerJoin(department.location, location).fetchJoin()
                .innerJoin(location.country, country).fetchJoin()
                .innerJoin(country.region, region).fetchJoin()
                .where(department.departmentId.eq(id))
                .orderBy(department.departmentId.asc())
                .fetchOne())
                ;
    }

}
