package com.example.sample.department.domain.entity.detail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "locations")
@Entity
public class Location {

    @Id
    private Long locationId;

    @JoinColumn(name = "country_id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    private String stateProvince;

    private String city;

    private String postalCode;

    private String streetAddress;

}
