package com.kcc.banking.domain.employee.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.NotFoundException;
import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.request.EmployeeSearch;
import com.kcc.banking.domain.employee.dto.request.EmployeeUpdate;
import com.kcc.banking.domain.employee.dto.response.*;
import com.kcc.banking.domain.employee.mapper.EmployeeMapper;
import com.kcc.banking.domain.trade.dto.response.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CommonService commonService;

    public List<EmployeeDataOfList> getEmployeeListOfBranch() {
        return employeeMapper.findAllOfBranch(AuthenticationUtils.getLoginMemberId());
    }


    /**
     * @return
     * @Description 비밀번호 암호화 해야함
     */
    public Long createEmployee(EmployeeCreate employeeCreate) {
        int employeeSeq = employeeMapper.getEmployeeSeq();
        CurrentData currentData = commonService.getCurrentData();
        int year = Year.now().getValue(); // 올해 년도

        employeeCreate.setPassword(passwordEncoder.encode(employeeCreate.getPassword()));
        employeeCreate.setCommonColumn(currentData);
        String id = String.format("%d%s%04d", year, currentData.getBranchId(), employeeSeq);
        employeeCreate.setId(Long.valueOf(id));

        employeeMapper.save(employeeCreate);
        return employeeCreate.getId();
    }


    public Long updateEmployee(EmployeeUpdate employeeUpdate) {

        employeeUpdate.setCommonColumn(commonService.getCurrentData());
        employeeMapper.update(employeeUpdate);
        return employeeUpdate.getId();

    }

    public EmployeeSearchResult getEmployeeListByOption(EmployeeSearch employeeSearch) {
        String branchId = commonService.getCurrentBusinessDateAndBranchId().getBranchId();
        employeeSearch.setBranchId(branchId);

        int totalCount = employeeMapper.getEmployeeCount(employeeSearch);
        PageDTO pageDTO = new PageDTO(employeeSearch.getCriteria(), totalCount);

        List<EmployeeSearchInfo> employees = employeeMapper.findEmployees(employeeSearch);
        return EmployeeSearchResult.of(employees, pageDTO);
    }

    public EmployeeDetail getEmployeeDetail(Long id) {
        Timestamp businessDate = commonService.getCurrentBusinessDay().getBusinessDate();

        EmployeeDetail empDetail = employeeMapper.findEmpDetailById(id);
        empDetail.setCurrentBusinessDate(businessDate);

        return empDetail;
    }

    public AuthData getAuthData() {
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        if (loginMemberId == null) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_MEMBER);
        }

        return employeeMapper.findAuthDataById(loginMemberId);
    }

    public EmployeeSearchResult getEmployeeListByOption2(EmployeeSearch employeeSearch) {
        String branchId = commonService.getCurrentBusinessDateAndBranchId().getBranchId();
        employeeSearch.setBranchId(branchId);

        int totalCount = employeeMapper.getEmployeeCount(employeeSearch);
        PageDTO pageDTO = new PageDTO(employeeSearch.getCriteria(), totalCount);

        List<EmployeeSearchInfo> employees = employeeMapper.findEmployees2(employeeSearch);
        return EmployeeSearchResult.of(employees, pageDTO);
    }
}
