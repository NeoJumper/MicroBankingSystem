package com.kcc.banking.domain.account_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AccountStatus {
    private String id;
    private String status;
    private Long modifierId;
    private BigDecimal balance;

    @Builder
    public AccountStatus(String id, String status, Long modifierId, BigDecimal balance) {
        this.id = id;
        this.status = status;
        this.modifierId = modifierId;
        this.balance = balance;
    }
}
