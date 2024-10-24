package com.kcc.banking.domain.dashboard.mapper;


import com.kcc.banking.domain.dashboard.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardMapper {
    List<AccountOpenRatioChart> findAccountOpenRatioByRegistrantId(Long registrantId);

    List<YearlyTransactionComparisonChart> findYearlyTransactionComparison(Map<String, Object> params);

    List<DailyTransactionTypeChart> findDailyTransactionTypes(Map<String, Object> params);

    List<DailyTransactionVolumeChart> findDailyTransactionVolume(String branchId, String today);

    List<WeeklyTransactionVolumeChart> findWeeklyTransactionVolume(String branchId, String today);

    List<MonthlyTransactionVolumeChart> findMonthlyTransactionVolume(String branchId, String today);

    List<EmployeeTransactionVolumeChart> findEmployeeTransactionByBranchId(Long branchId);

    /**
     * 직원별 거래 유형 및 거래량을 조회
     *
     * @param branchId 지점 ID
     * @return 직원별 거래 유형 및 거래량 리스트
     */
    List<EmployeeTransactionVolumeChart> findEmployeeTransactionTypes(Long branchId);

}


