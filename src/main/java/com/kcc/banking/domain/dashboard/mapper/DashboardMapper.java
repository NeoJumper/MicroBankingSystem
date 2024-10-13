package com.kcc.banking.domain.dashboard.mapper;


import com.kcc.banking.domain.dashboard.dto.response.AccountOpenRatioChart;
import com.kcc.banking.domain.dashboard.dto.response.YearlyTransactionComparisonChart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardMapper {
    List<AccountOpenRatioChart> findAccountOpenRatioByRegistrantId(Long registrantId);

    List<YearlyTransactionComparisonChart> findYearlyTransactionComparison(Map<String, Object> params);
}
