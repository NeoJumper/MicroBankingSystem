package com.kcc.banking.domain.business_day_close.mapper;

import com.kcc.banking.domain.business_day_close.dto.request.*;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface BusinessDayCloseMapper {

    /**
     *  SELECT
     */
    ClosingData findClosingData(BusinessDateAndEmployeeId businessDateAndEmployeeId);

    List<ClosingData> findClosingDataList(BusinessDateAndBranchId businessDateAndBranchId);

    String findClosingTradeNumber(BusinessDateAndBranchId businessDateAndBranchId);

    String findBranchClosingStatusByDate(BusinessDateAndBranchId  businessDateAndBranchId);

    long getNextTradeNumberVal();

    /**
     *  INSERT
     */
    void batchInsertEmployeeClosing(List<EmployeeClosingCreate> workerDataList);

    void insertBranchClosing(BranchClosingCreate branchClosingCreate);

    /**
     *  UPDATE
     */
    void updateEmployeeClosing(EmployeeClosingUpdate employeeClosingUpdate);

    void updateBranchClosing(BranchClosingUpdate branchClosingUpdate);

    BigDecimal findBranchClosingVaultCash(BusinessDateAndBranchId currentBusinessDateAndBranchId);

    BigDecimal findEmployeeClosingTotalDeposit(BusinessDateAndEmployeeId currentBusinessDateAndBranchId);

    BigDecimal findEmployeeClosingTotalWithdrawal(BusinessDateAndEmployeeId currentBusinessDateAndBranchId);

    int updateAdditionalCashExchange(EmployeeAdditionalUpdate managerUpdate);

    /**
     *  DELETE
     */
    void deleteEmployeeClosing(String businessDateAndEmployeeId);

    void deleteBranchClosing(String businessDateAndBranchId);

    String getStatusById(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId);
}
