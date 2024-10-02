package com.kcc.banking.domain.business_day.service;

import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayUpdate;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.event.BusinessDayChangeEvent;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessDayService {

    private final BusinessDayMapper businessDayMapper;
    private final ApplicationEventPublisher eventPublisher;

    public BusinessDay getCurrentBusinessDay() {
        return businessDayMapper.findCurrentBusinessDay();
    }

    public BusinessDay getNextBusinessDay() {
        return businessDayMapper.findNextBusinessDay();
    }

    public BusinessDay getPrevBusinessDay() {
        return businessDayMapper.findPrevBusinessDay();
    }


    public void closeBusinessDay(BusinessDayChange businessDayChange) {

        /**
         * 요청값에서 변경될 날짜를 검증해야함
         */
        String currentBusinessDate = getCurrentBusinessDay().getBusinessDate();
        String nextBusinessDate = getNextBusinessDay().getBusinessDate();


        businessDayStatusToClosed(currentBusinessDate);
        businessDayStatusToOPEN(nextBusinessDate);



        eventPublisher.publishEvent(new BusinessDayChangeEvent(businessDayChange));
    }

    public void businessDayStatusToClosed(String targetDate) {
        businessDayMapper.updateStatus(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("CLOSED")
                .isCurrentBusinessDay("FALSE")
                .build()
        );

    }
    public void businessDayStatusToOPEN(String targetDate) {
        businessDayMapper.updateStatus(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("OPEN")
                .isCurrentBusinessDay("TRUE")
                .build()
        );

    }
}
