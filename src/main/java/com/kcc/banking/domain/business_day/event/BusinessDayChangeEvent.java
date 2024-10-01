package com.kcc.banking.domain.business_day.event;

import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BusinessDayChangeEvent {

    private BusinessDayChange businessDayChange;

    public BusinessDayChangeEvent(BusinessDayChange businessDayChange) {
        this.businessDayChange = businessDayChange;
    }
}
