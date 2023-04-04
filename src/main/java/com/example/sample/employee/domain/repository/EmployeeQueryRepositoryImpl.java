package com.example.sample.employee.domain.repository;

import com.example.sample.common.utils.CustomQueryDsl;
import com.example.sample.employee.domain.entity.Employee;
import com.example.sample.employee.query.dto.EmployeeQueryDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.sample.employee.domain.entity.QEmployee.employee;
import static com.example.sample.employee.domain.entity.QJob.job;
import static com.example.sample.employee.domain.entity.value.QHistory.history;
import static org.springframework.util.StringUtils.hasText;

public class EmployeeQueryRepositoryImpl implements EmployeeQueryRepository {

    private JPAQueryFactory jpaQueryFactory;

    public EmployeeQueryRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Employee> query(Pageable pageable, EmployeeQueryDto employeeQueryDto) {
        List<OrderSpecifier<?>> orders = getOrders(pageable);
        QueryResults<Employee> employeeQueryResults = jpaQueryFactory.selectFrom(employee)
                .innerJoin(employee.job, job).fetchJoin()
                .leftJoin(employee.historyList, history).fetchJoin()
                .where(
                        firstNameContains(employeeQueryDto.getFirstName()),
                        lastNameContains(employeeQueryDto.getLastName()),
                        emailContains(employeeQueryDto.getEmail()),
                        phoneNumberContains(employeeQueryDto.getPhoneNumber()),
                        hireDateBetween(employeeQueryDto.getHireDate())
                )
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(employeeQueryResults.getResults(), pageable, employeeQueryResults.getTotal());
    }

    private BooleanExpression hireDateBetween(LocalDate hireDate) {
        return hireDate != null ? employee.hireDate.eq(hireDate) : null;
    }

    private BooleanExpression phoneNumberContains(String phoneNumber) {
        return hasText(phoneNumber) ? employee.phoneNumber.contains(phoneNumber) : null;
    }

    private BooleanExpression emailContains(String email) {
        return hasText(email) ? employee.email.contains(email) : null;
    }

    private BooleanExpression lastNameContains(String lastName) {
        return hasText(lastName) ? employee.name.lastName.contains(lastName) : null;
    }

    private BooleanExpression firstNameContains(String firstName) {
        return hasText(firstName) ? employee.name.firstName.contains(firstName) : null;
    }

    private List<OrderSpecifier<?>> getOrders(Pageable pageable) {
        return pageable.getSort().isEmpty() ? new ArrayList<>() : pageable.getSort()
                .stream()
                .map(order -> CustomQueryDsl.getSortedColumn(order.getDirection().isAscending() ? Order.ASC : Order.DESC, employee, order.getProperty()))
                .collect(Collectors.toList());
    }

}
