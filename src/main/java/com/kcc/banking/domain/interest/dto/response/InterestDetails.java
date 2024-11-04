package com.kcc.banking.domain.interest.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class InterestDetails {

    private String creationDate;
    private BigDecimal balance;
    private BigDecimal interestRate;
    private BigDecimal preferentialInterestRate;
    private BigDecimal amount;

    @Builder
    public InterestDetails(String creationDate, BigDecimal balance, BigDecimal interestRate, BigDecimal preferentialInterestRate, BigDecimal amount) {
        this.creationDate = creationDate;
        this.balance = balance;
        this.interestRate = interestRate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.amount = amount;
    }
}
