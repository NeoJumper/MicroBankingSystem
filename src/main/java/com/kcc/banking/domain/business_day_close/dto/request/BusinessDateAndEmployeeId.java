package com.kcc.banking.domain.business_day_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BusinessDateAndEmployeeId {

    private String businessDate;
    private Long employeeId;

    @Builder
    public BusinessDateAndEmployeeId(String businessDate, Long employeeId) {
        this.businessDate = businessDate;
        this.employeeId = employeeId;
    }
}
