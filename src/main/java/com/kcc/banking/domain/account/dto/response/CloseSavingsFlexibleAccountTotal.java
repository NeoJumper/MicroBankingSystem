package com.kcc.banking.domain.account.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CloseSavingsFlexibleAccountTotal {

    private String accId;
    private String openDate;
    private String status;
    private String customerName;
    private Long customerId;
    private String productName;
    private String securityLevel;
    private BigDecimal balance;
    private BigDecimal dailyLimit;
    private BigDecimal perTradeLimit;

    private String productType;
    private String interestCalculationMethod;

}
