package com.kcc.banking.domain.business_day.mapper;

import com.kcc.banking.domain.business_day.dto.request.BusinessDayUpdate;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface BusinessDayMapper {
    BusinessDay findCurrentBusinessDay();

    BusinessDay findNextBusinessDay();

    BusinessDay findPrevBusinessDay();

    BusinessDay findByDate(String date);

    int update(BusinessDayUpdate businessDayUpdate);
}
