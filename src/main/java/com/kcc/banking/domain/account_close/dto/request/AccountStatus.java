package com.kcc.banking.domain.account_close.dto.request;

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
    private Long modifierId;

    @Builder
    public AccountStatus(String id, String status, Long modifierId) {
        this.id = id;
        this.status = status;
        this.modifierId = modifierId;
    }
}
