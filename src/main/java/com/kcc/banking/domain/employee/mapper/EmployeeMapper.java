package com.kcc.banking.domain.employee.mapper;

import com.kcc.banking.domain.employee.dto.response.EmployeeDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<EmployeeDetail> findAll();


}
