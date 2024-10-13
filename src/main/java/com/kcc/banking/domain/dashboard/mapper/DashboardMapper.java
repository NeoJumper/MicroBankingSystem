package com.kcc.banking.domain.dashboard.mapper;


import com.kcc.banking.domain.dashboard.dto.response.AccountOpenRatioChart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardMapper {
    List<AccountOpenRatioChart> findAccountOpenRatioByRegistrantId(Long registrantId);
}
