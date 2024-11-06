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

    /*---------- 초기 입력될 정보---------- */
    private String accountId; // 계좌번호 or 출금계좌
    private String accountStatus; // 계좌상태 OPN /CLS
    private String customerName; // 고객명
    private long customerId; // 고객ID
    private String productName; // 상품명

    /*---------- 계좌 정보---------- */
    private BigDecimal accountInterestRate; // 우대이율
    private BigDecimal accountBalance; //잔액
    private String openDate; // 적금 가입 날짜 : startDate-> openDate로 변경
    private String startDate;

    /*---------- 상품 정보---------- */
    private BigDecimal productInterestRate; // 상품이율
    private BigDecimal productTaxRate; // 상품 세율
    private String productType; // 단복리 FIXED
    private String productPeriod; // 상품기간

    /*---------- 이율 계산 정보---------- */
    // 총 이자액
    private BigDecimal amountSum;
    // 총 이자액 + 잔액 = 지급액
    private BigDecimal totalBalanceSum;


    /*---------- 자동이체 정보---------- */
    private BigDecimal fixedAmount;
    private String autoTransferStartDate;
    private String autoTransferEndDate;
    private String autoTransferPeriod; // 기간  1,2,3 ~ 12 (달)
    private String createDate;
    private String targetAccId; // 입금 계좌


    // 자동이체 횟수
    private String autoTransferCount;

    /* -------------이상한정보?------------- */
    private String accId; //???


    @Builder
    public CloseSavingsAccountTotal(String autoTransferCount, String accountId, String accountStatus, String customerName, long customerId, String productName, BigDecimal accountInterestRate, BigDecimal productInterestRate, BigDecimal accountBalance, BigDecimal productTaxRate, BigDecimal amountSum, AutoTransferClose autoTransferClose, String productType) {
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
        this.autoTransferCount =autoTransferCount;


    }
}
