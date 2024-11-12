package com.kcc.banking.domain.business_day_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class BusinessDateAndEmployeeId {

    private Timestamp businessDate;
    private Long employeeId;

    @Builder
    public BusinessDateAndEmployeeId(Timestamp businessDate, Long employeeId) {
        this.businessDate = businessDate;
        this.employeeId = employeeId;
    }
}
