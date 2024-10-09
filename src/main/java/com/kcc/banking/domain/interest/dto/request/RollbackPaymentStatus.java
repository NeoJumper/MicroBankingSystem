package com.kcc.banking.domain.interest.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class RollbackPaymentStatus {
    private long branchId;
    private long modifierId;
    private String accId;
    private Timestamp expireDate;

    @Builder
    public RollbackPaymentStatus(long branchId, long modifierId, String accId, Timestamp expireDate) {
        this.branchId = branchId;
        this.modifierId = modifierId;
        this.accId = accId;
        this.expireDate = expireDate;
    }
}
