package com.kcc.banking.domain.business_day.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class BusinessDayUpdate {
    private String status;
    private String isCurrentBusinessDay;
    private String modifierId;
    private Timestamp targetDate;


    @Builder
    public BusinessDayUpdate(String status, String isCurrentBusinessDay, String targetDate, String modifierId) {
        this.status = status;
        this.isCurrentBusinessDay = isCurrentBusinessDay;
        this.targetDate = Timestamp.valueOf(targetDate);
        this.modifierId = modifierId;
    }
}
