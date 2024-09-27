package com.kcc.banking.domain.employee.mapper;

import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.request.EmployeeSearch;
import com.kcc.banking.domain.employee.dto.request.EmployeeUpdate;
import com.kcc.banking.domain.employee.dto.response.EmployeeDataOfList;
import com.kcc.banking.domain.employee.dto.response.EmployeeDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<EmployeeDataOfList> findAll();

    void save(EmployeeCreate employeeCreate);

    List<EmployeeDataOfList> findBySearchOption(EmployeeSearch employeeSearch);

    void update(EmployeeUpdate employeeUpdate);

    EmployeeDetail findById(Long id);
}
