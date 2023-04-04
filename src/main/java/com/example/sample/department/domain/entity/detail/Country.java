package com.example.sample.department.domain.entity.detail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "countries")
@Entity
public class Country {

    @Id
    private String countryId;

    private String countryName;
    @JoinColumn(name = "region_id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Region region;

}
