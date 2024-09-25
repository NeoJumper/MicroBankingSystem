package com.kcc.banking.domain.account_close.dto.response;

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
    private BigDecimal interestRateSum;
    private BigDecimal amountSum;

    @Builder
    public InterestSum(String accountId, BigDecimal interestRateSum, BigDecimal amountSum) {
        this.accountId = accountId;
        this.interestRateSum = interestRateSum;
        this.amountSum = amountSum;
    }
}
