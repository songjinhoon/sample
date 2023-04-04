package com.example.sample.employee.command.application;

import com.example.sample.common.exception.SalaryUpdateException;
import com.example.sample.employee.command.dto.SalaryUpdateDto;
import com.example.sample.employee.domain.entity.Employee;
import com.example.sample.employee.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SalaryUpdate {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void update(Long departmentId, SalaryUpdateDto salaryUpdateDto) {
        List<Employee> employeeList = employeeRepository.findByDepartmentId(departmentId);

        employeeList.forEach(employee -> {
            float value = salaryUpdateDto.getPercent() / 100;
            BigDecimal multiply = employee.getSalary().multiply(BigDecimal.valueOf(value)).setScale(2, RoundingMode.HALF_UP);
            BigDecimal add = employee.getSalary().add(multiply);

            if (add.compareTo(employee.getJob().getMinSalary()) > 0 && add.compareTo(employee.getJob().getMaxSalary()) < 0) {
                log.info("employeeId: {}, salary: {}, addSalary: {}, sum: {}", employee.getEmployeeId(), employee.getSalary(), multiply, add);
                employee.updateSalary(add);
            } else {
                log.error("employeeId: {}, salary: {}, minSalary: {}, maxSalary: {}, sum: {}", employee.getEmployeeId(), employee.getSalary(), employee.getJob().getMinSalary(), employee.getJob().getMaxSalary(), add);
                throw new SalaryUpdateException("employeeId: " + employee.getEmployeeId() + ", salary update impossible");
            }
        });
    }

}
