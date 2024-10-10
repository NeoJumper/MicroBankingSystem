package com.kcc.banking.domain.interest.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class AccountIdWithExpireDate {
    private String accountId;
    private String expireDate;

    @Builder
    public AccountIdWithExpireDate(String accountId, String expireDate) {
        this.accountId = accountId;
        this.expireDate = expireDate;
    }
}
