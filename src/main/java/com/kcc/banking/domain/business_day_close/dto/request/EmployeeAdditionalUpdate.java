package com.kcc.banking.domain.business_day_close.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeAdditionalUpdate {
    private String closingDate;
    private Long registrantId;
    private BigDecimal amount;
    private String transactionType;
    private Long modifierId;

    @Builder
    public EmployeeAdditionalUpdate(String closingDate, Long registrantId, BigDecimal amount, String transactionType, Long modifierId) {
        this.closingDate = closingDate;
        this.registrantId = registrantId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.modifierId = modifierId;
    }
}
