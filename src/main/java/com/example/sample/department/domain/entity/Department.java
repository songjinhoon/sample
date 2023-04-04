package com.example.sample.department.domain.entity;

import com.example.sample.department.domain.entity.detail.Location;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "departments")
@Entity
public class Department {

    @Id
    private Long departmentId;

    private String departmentName;

    private Long managerId;

    @JoinColumn(name = "location_id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;

}
