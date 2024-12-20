package com.kcc.banking.domain.employee.mapper;

import com.kcc.banking.domain.customer.dto.request.CustomerSearch;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.request.EmployeeSearch;
import com.kcc.banking.domain.employee.dto.request.EmployeeUpdate;
import com.kcc.banking.domain.employee.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<EmployeeDataOfList> findAllOfBranch(long branchId);

    void save(EmployeeCreate employeeCreate);

    List<EmployeeDataOfList> findAllOfBranchBySearchOption(EmployeeSearch employeeSearch);

    void update(EmployeeUpdate employeeUpdate);

    EmployeeDetail findById(Long id);

    EmployeeDetail findEmpDetailById(Long id);

    AuthData findAuthDataById(Long id);

    int getEmployeeSeq();

    int getEmployeeCount(EmployeeSearch employeeSearch);

    List<EmployeeSearchInfo> findEmployees(EmployeeSearch searchDTO);

    List<EmployeeSearchInfo> findEmployees2(EmployeeSearch employeeSearch);
}
