package com.kcc.banking.domain.dashboard.service;

import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.dashboard.dto.response.AccountOpenRatioChart;
import com.kcc.banking.domain.dashboard.dto.response.YearlyTransactionComparisonChart;
import com.kcc.banking.domain.dashboard.mapper.DashboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardMapper dashboardMapper;
    private final CommonService commonService;

    public List<AccountOpenRatioChart> getAccountOpenRatioChart() {
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        return dashboardMapper.findAccountOpenRatioByRegistrantId(currentBusinessDateAndEmployeeId.getEmployeeId());
    }

    public List<YearlyTransactionComparisonChart> getYearlyTransactionComparison(Map<String, Object> params) {
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        params.put("registrantId", currentBusinessDateAndEmployeeId.getEmployeeId());
        return dashboardMapper.findYearlyTransactionComparison(params);
    }

}
