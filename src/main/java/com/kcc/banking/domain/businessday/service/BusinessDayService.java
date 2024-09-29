package com.kcc.banking.domain.businessday.service;

import com.kcc.banking.domain.businessday.dto.BusinessDay;
import com.kcc.banking.domain.businessday.mapper.BusinessDayMapper;
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
}
