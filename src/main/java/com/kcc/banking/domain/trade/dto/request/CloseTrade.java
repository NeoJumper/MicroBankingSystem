package com.kcc.banking.domain.trade.dto.request;

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
    private long branchId; // 계좌 지점이랑 다름
    private BigDecimal amount;
    private String description;
    private BigDecimal balance;
    private String tradeType;
    private String businessDay;

    @Builder
    public CloseTrade(long id, String accId, long registrantId, long branchId, BigDecimal amount, String description, BigDecimal balance, String tradeType, String businessDay) {
        this.id = id;
        this.accId = accId;
        this.registrantId = registrantId;
        this.branchId = branchId;
        this.amount = amount;
        this.description = description;
        this.balance = balance;
        this.tradeType = tradeType;
        this.businessDay = businessDay;
    }
}
