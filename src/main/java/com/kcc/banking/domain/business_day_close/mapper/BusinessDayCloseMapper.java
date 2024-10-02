package com.kcc.banking.domain.business_day_close.mapper;

import com.kcc.banking.domain.business_day_close.dto.request.BranchClosingCreate;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.business_day_close.dto.request.EmployeeClosingCreate;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessDayCloseMapper {

    ClosingData findClosingData(BusinessDateAndEmployeeId businessDateAndEmployeeId);

    List<ClosingData> findClosingDataList(BusinessDateAndBranchId businessDateAndBranchId);

    void employeeDeadlineStatusToClosed(BusinessDateAndEmployeeId businessDateAndEmployeeId);

    void branchDeadlineStatusToClosed(BusinessDateAndBranchId businessDateAndBranchId);

    long getNextTradeNumberVal();

    void batchInsertEmployeeClosing(List<EmployeeClosingCreate> workerDataList);

    void insertBranchClosing(BranchClosingCreate branchClosingCreate);

    String  findClosingTradeNumber(BusinessDateAndBranchId businessDateAndBranchId);

    String findBranchClosingStatusByDate(BusinessDateAndBranchId  businessDateAndBranchId);
}
