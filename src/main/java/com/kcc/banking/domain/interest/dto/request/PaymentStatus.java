package com.kcc.banking.domain.interest.dto.request;

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
    private long modifierId;
    private String accId;
    private Timestamp payDate;


    @Builder
    public PaymentStatus(long branchId, Timestamp payDate, long modifierId, String accId) {
        this.branchId = branchId;
        this.payDate = payDate;
        this.modifierId = modifierId;
        this.accId = accId;
    }
}
