package com.kcc.banking.domain.accountclose.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountStatus {
    private String id;
    private String status;
    private String modifier;

    @Builder
    public AccountStatus(String id, String status) {
        this.id = id;
        this.status = status;
    }
}
