package com.kcc.banking.domain.dashboard.mapper;


import com.kcc.banking.domain.dashboard.dto.response.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardMapper {
    List<AccountOpenRatioChart> findAccountOpenRatioByRegistrantId(Long registrantId);

    List<YearlyTransactionComparisonChart> findYearlyTransactionComparison(Map<String, Object> params);

    List<DailyTransactionTypeChart> findDailyTransactionTypes(Map<String, Object> params);

    List<DailyTransactionVolumeChart> findDailyTransactionVolume(@Param("branchId") String branchId, @Param("today") String today);

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

    /**
     * 직원의 이번달 거래 유형
     * @param employeeId
     * @return
     */
    List<TransactionTypeCount> findCurrentMonthTransactionTypes(@Param("employeeId") Long employeeId, @Param("today") String today);

    /**
     * 직원의 이번 달 일별 거래량 추이
     * @return
     */
    List<DailyTransactionVolumeChart> findEmployeeDailyTransactionVolume(Map<String, Object> params);

    /**
     * 매니저용 월별 거래 유형
     * @param branchId
     * @param today
     * @return
     */
    List<TransactionTypeCount> findMonthlyTransactionTypes(@Param("branchId") Long branchId, @Param("today") String today);
}


