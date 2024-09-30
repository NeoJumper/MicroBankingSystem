package com.kcc.banking.domain.business_day.mapper;

import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessDayMapper {
    BusinessDay findCurrentBusinessDay();

    BusinessDay findNextBusinessDay();

    BusinessDay findPrevBusinessDay();


}
