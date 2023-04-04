package com.example.sample.employee.domain.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Embeddable
public class Name {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
