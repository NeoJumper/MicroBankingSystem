package com.kcc.banking.domain.account.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class SavingsAccountOfModal {

    private String accId;
    private String openDate;
    private String status;
    private String customerName;
    private String productName;

    private BigDecimal balance;


}
