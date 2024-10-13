package com.kcc.banking.domain.dashboard.service;

import com.kcc.banking.domain.dashboard.dto.response.AccountOpenRatioChart;
import com.kcc.banking.domain.dashboard.mapper.DashboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardMapper dashboardMapper;

    public List<AccountOpenRatioChart> getAccountOpenRatioChart(Long registrantId) {
        return dashboardMapper.findAccountOpenRatioByRegistrantId(registrantId);
    }
}
