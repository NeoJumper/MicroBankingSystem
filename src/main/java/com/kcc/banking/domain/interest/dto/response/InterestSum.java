package com.kcc.banking.domain.interest.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class InterestSum {
    private String accountId;
    private BigDecimal amountSum;

    @Builder
    public InterestSum(String accountId, BigDecimal amountSum) {
        this.accountId = accountId;
        this.amountSum = amountSum;
    }
}
