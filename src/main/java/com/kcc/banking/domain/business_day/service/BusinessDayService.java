package com.kcc.banking.domain.business_day.service;


import com.kcc.banking.domain.business_day.dto.request.BusinessDayUpdate;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;

import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import com.kcc.banking.domain.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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


    // DB업데이트 및 변경사항 객체에 반영
    public void businessDayChange(BusinessDay currentBusinessDay, BusinessDay nextBusinessDay) {
        int updateResult1 = currentBusinessDayToFalse(currentBusinessDay.getBusinessDate());
        int updateResult2 = businessDayStatusToOPEN(nextBusinessDay.getBusinessDate());

        if(updateResult1 == 1 && updateResult2 == 1) {
            reflectUpdateResult(currentBusinessDay, nextBusinessDay);
        }
    }

    public int currentBusinessDayToFalse(String targetDate) {
        return businessDayMapper.update(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .isCurrentBusinessDay("FALSE")
                .build()
        );

    }
    public int businessDayStatusToOPEN(String targetDate) {
        return businessDayMapper.update(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("OPEN")
                .isCurrentBusinessDay("TRUE")
                .build()
        );

    }
    public int businessDayStatusToClosed(String targetDate) {
        return businessDayMapper.update(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("CLOSED")
                .build()
        );

    }
    private void reflectUpdateResult(BusinessDay currentBusinessDay, BusinessDay nextBusinessDay) {
        currentBusinessDay.toClose();
        nextBusinessDay.toOpen();
    }
}
