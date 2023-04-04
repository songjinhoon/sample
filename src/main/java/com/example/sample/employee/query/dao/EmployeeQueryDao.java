package com.example.sample.employee.query.dao;

import com.example.sample.employee.query.dto.EmployeeDepartmentView;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeQueryDao {

    EmployeeDepartmentView find(Long id);

}
