package com.kcc.banking.domain.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionTypeCount {
    private String transactionType;
    private int transactionCount;

    @Builder
    public TransactionTypeCount(String transactionType, int transactionCount) {
        this.transactionType = transactionType;
        this.transactionCount = transactionCount;
    }
}
