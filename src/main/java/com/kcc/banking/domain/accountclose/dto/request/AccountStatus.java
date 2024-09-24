package com.kcc.banking.domain.accountclose.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountStatus {
    private String id;
    private String status;
    private String modifier;

    @Builder
    public AccountStatus(String id, String status, String modifier) {
        this.id = id;
        this.status = status;
        this.modifier = modifier;
    }
}
