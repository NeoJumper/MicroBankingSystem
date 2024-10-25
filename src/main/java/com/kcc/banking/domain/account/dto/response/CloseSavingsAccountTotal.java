package com.kcc.banking.domain.account.dto.response;

import com.kcc.banking.domain.auto_transfer.dto.response.AutoTransferClose;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CloseSavingsAccountTotal {

    private String accountId;
    private String accountStatus;
    private String customerName;
    private long customerId;
    private String productName;
    private BigDecimal accountInterestRate;
    private BigDecimal productInterestRate;
    private BigDecimal accountBalance;
    private BigDecimal productTaxRate;

    // 단복리
    private String productType;
    // 상품기간
    private String productPeriod;
    // 적금 가입 날짜
    private String startDate;

    // 총 이자액
    private BigDecimal amountSum;
    // 자동이체 횟수
    private BigDecimal autoTransferCount;

    // 자동이체 정보
    private BigDecimal fixedAmount;
    private String autoTransferStartDate;
    private String autoTransferEndDate;
    private String autoTransferPeriod;
    private String createDate;

    // 총 이자액 + 잔액 = 지급액
    private BigDecimal totalBalanceSum;

    @Builder
    public CloseSavingsAccountTotal(String accountId, String accountStatus, String customerName, long customerId, String productName, Timestamp amountDate, BigDecimal accountInterestRate, BigDecimal productInterestRate, BigDecimal accountBalance, BigDecimal productTaxRate, BigDecimal amountSum, AutoTransferClose autoTransferClose, String productType) {
        this.accountId = accountId;
        this.accountStatus = accountStatus;
        this.customerName = customerName;
        this.customerId = customerId;
        this.productName = productName;
        this.accountInterestRate = accountInterestRate;
        this.productInterestRate = productInterestRate;
        this.accountBalance = accountBalance;
        this.productTaxRate = productTaxRate;
        this.amountSum = amountSum;
        this.productType = productType;


    }
}
