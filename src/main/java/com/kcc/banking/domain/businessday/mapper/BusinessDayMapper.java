package com.kcc.banking.domain.businessday.mapper;

import com.kcc.banking.domain.businessday.dto.CurrentBusinessDay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessDayMapper {
    CurrentBusinessDay findCurrentBusinessDay();
}
