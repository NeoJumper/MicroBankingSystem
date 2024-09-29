package com.kcc.banking.domain.businessday.service;

import com.kcc.banking.domain.businessday.dto.CurrentBusinessDay;
import com.kcc.banking.domain.businessday.mapper.BusinessDayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessDayService {

    private final BusinessDayMapper businessDayMapper;

    public CurrentBusinessDay getCurrentBusinessDay() {
        return businessDayMapper.findCurrentBusinessDay();
    }
}
