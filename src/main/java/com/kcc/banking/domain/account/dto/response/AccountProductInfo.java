package com.kcc.banking.domain.account.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class AccountProductInfo {

    private BigDecimal interestRate;
    private String name;
    private int id;


    public AccountProductInfo(BigDecimal interestRate, String name, int id) {
        this.interestRate = interestRate;
        this.name = name;
        this.id = id;
    }


}
