package com.kcc.banking.domain.interest.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class AccountIdWithExpireDate {
    private String accountId;
    private Timestamp expireDate;

    @Builder
    public AccountIdWithExpireDate(String accountId, Timestamp expireDate) {
        this.accountId = accountId;
        this.expireDate = expireDate;
    }
}
