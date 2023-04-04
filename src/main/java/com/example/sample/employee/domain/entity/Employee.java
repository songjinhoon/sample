package com.example.sample.employee.domain.entity;


import com.example.sample.employee.domain.entity.value.History;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    private Long employeeId;

    @Embedded
    private Name name;

    private String email;

    private String phoneNumber;

    private LocalDate hireDate;

    @JoinColumn(name = "job_id")
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Job job;

    @Column(precision = 8, scale = 2)
    private BigDecimal salary;

    @Column(precision = 2, scale = 2)
    private BigDecimal commissionPct;

    private Long managerId;

    private Long departmentId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "job_history", joinColumns = @JoinColumn(name = "employee_id"))
    private List<History> historyList;

    public void updateSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
