package com.example.sample.employee.controller;

import com.example.sample.employee.command.application.SalaryUpdate;
import com.example.sample.employee.command.dto.SalaryUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/employee")
@RestController
public class EmployeeUpdateController {

    private final SalaryUpdate salaryUpdate;

    @PutMapping("/department/{departmentId}")
    public ResponseEntity<?> update(@PathVariable Long departmentId, @RequestBody @Valid SalaryUpdateDto salaryUpdateDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        salaryUpdate.update(departmentId, salaryUpdateDto);
        return ResponseEntity.noContent().build();
    }

}
