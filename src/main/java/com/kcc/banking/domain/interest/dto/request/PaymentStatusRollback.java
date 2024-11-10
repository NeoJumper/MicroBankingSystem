package com.kcc.banking.domain.interest.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PaymentStatusRollback {
    private long branchId;
    private long modifierId;
    private String accId;
    private String expireDate;

    @Builder
    public PaymentStatusRollback(long branchId, long modifierId, String accId, String expireDate) {
        this.branchId = branchId;
        this.modifierId = modifierId;
        this.accId = accId;
        this.expireDate = expireDate;
    }
}
