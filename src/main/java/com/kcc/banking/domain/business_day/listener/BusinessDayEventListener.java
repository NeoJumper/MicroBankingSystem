package com.kcc.banking.domain.business_day.listener;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import com.kcc.banking.domain.business_day.event.BusinessDayChangeEvent;
import com.kcc.banking.domain.business_day_close.dto.request.EmployeeClosingCreate;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import com.kcc.banking.domain.interest.service.InterestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class BusinessDayEventListener {

    private final BusinessDayCloseService businessDayCloseService;


    @EventListener
    public void businessDayChangeEventListener(BusinessDayChangeEvent event)
    {
        Long tradeNumber = businessDayCloseService.createClosingData(event.getBusinessDayChange());

    }


}
