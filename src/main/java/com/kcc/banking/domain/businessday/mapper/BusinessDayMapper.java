package com.kcc.banking.domain.businessday.mapper;

import com.kcc.banking.domain.businessday.dto.response.BusinessDay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessDayMapper {
    BusinessDay findCurrentBusinessDay();

    BusinessDay findNextBusinessDay();

    BusinessDay findPrevBusinessDay();
}
