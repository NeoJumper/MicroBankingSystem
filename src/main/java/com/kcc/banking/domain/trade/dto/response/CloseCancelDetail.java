package com.kcc.banking.domain.trade.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class CloseCancelDetail {

    private String customerName;
    private String accountId;
    private String productName;
    private BigDecimal interRate; // 기본 이율
    private BigDecimal preInterRate; // 우대 이율
    private BigDecimal taxRate; // 세율
    private BigDecimal preTaxInterest; // 세전 이자
    private BigDecimal afterTaxInterest; // 세후 이자
    private BigDecimal balanceToRollback; // 취소 후 잔액

    @Builder
    public CloseCancelDetail(String customerName, String accountId, String productName, BigDecimal interRate, BigDecimal preInterRate, BigDecimal taxRate, BigDecimal preTaxInterest, BigDecimal afterTaxInterest, BigDecimal balanceToRollback) {
        this.customerName = customerName;
        this.accountId = accountId;
        this.productName = productName;
        this.interRate = interRate;
        this.preInterRate = preInterRate;
        this.taxRate = taxRate;
        this.preTaxInterest = preTaxInterest;
        this.afterTaxInterest = afterTaxInterest;
        this.balanceToRollback = balanceToRollback;
    }
}
