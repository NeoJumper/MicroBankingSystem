package com.kcc.banking.domain.account_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CloseTrade {
    private long id;
    private String accId;
    private long registrantId;
    private long branchId;
    private BigDecimal amount;

    @Builder
    public CloseTrade(long id, String accId, long registrantId, long branchId, BigDecimal amount) {
        this.id = id;
        this.accId = accId;
        this.registrantId = registrantId;
        this.branchId = branchId;
        this.amount = amount;
    }
}
