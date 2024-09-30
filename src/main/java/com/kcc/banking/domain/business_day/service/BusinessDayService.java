package com.kcc.banking.domain.business_day.service;

import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessDayService {

    private final BusinessDayMapper businessDayMapper;

    public BusinessDay getCurrentBusinessDay() {
        return businessDayMapper.findCurrentBusinessDay();
    }

    public BusinessDay getNextBusinessDay() {
        return businessDayMapper.findNextBusinessDay();
    }

    public BusinessDay getPrevBusinessDay() {
        return businessDayMapper.findPrevBusinessDay();
    }


}
