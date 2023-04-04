package com.example.sample.employee.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "jobs")
@Entity
public class Job {

    @Id
    private String jobId;

    private String jobTitle;

    @Column(precision = 8)
    private BigDecimal minSalary;

    @Column(precision = 8)
    private BigDecimal maxSalary;

}
