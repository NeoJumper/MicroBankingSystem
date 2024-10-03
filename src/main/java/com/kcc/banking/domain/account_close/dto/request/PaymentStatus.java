package com.kcc.banking.domain.account_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class PaymentStatus {
    private long branchId;
    private Timestamp payDate;
    private Timestamp modifierDate;
    private long modifierId;
    private String accId;

    @Builder
    public PaymentStatus(long branchId, Timestamp payDate, Timestamp modifierDate, long modifierId, String accId) {
        this.branchId = branchId;
        this.payDate = payDate;
        this.modifierDate = modifierDate;
        this.modifierId = modifierId;
        this.accId = accId;
    }
}
