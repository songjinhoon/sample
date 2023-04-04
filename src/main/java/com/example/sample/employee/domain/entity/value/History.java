package com.example.sample.employee.domain.entity.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class History {

    private LocalDate startDate;

    private LocalDate endDate;

    private String jobId;

    private Long departmentId;

}
