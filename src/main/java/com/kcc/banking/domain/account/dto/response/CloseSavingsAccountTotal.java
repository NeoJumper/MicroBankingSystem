package com.kcc.banking.domain.account.dto.response;

import com.kcc.banking.domain.auto_transfer.dto.response.AutoTransferClose;
import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
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
    private BigDecimal balance;

    // 예상 만기일
    private String expectedExpireDate;

    /*---------- 상품 정보---------- */
    private BigDecimal productInterestRate; // 상품이율
    private BigDecimal productTaxRate; // 상품 세율
    private String productType; // 단복리 FIXED
    private String productPeriod; // 상품기간

    /*---------- 자동이체 정보---------- */
    private BigDecimal fixedAmount;
    private String autoTransferStartDate;
    private String autoTransferEndDate;
    private String autoTransferPeriod; // 기간  1,2,3 ~ 12 (달)
    private String createDate;
    private String targetAccId; // 입금 계좌
    private String autoTransferId;

    // 자동이체 횟수
    private String autoTransferCount;


    /*---------- 이율 계산 정보---------- */
    // 총 이자액
    private BigDecimal interestCashSum;
    // 총 이자액 + 잔액 = 지급액
    private BigDecimal totalBalanceSum;
    // 해지 시 최종 이율
    private BigDecimal finalInterestRate;
    // 해지 시 최종 이자 금액 합산
    private BigDecimal totalInterestSum;
    // 해지 시 최종 이자*세율 계산 금액
    private BigDecimal totalInterestSumAfterTax;
    // 해지 시 최종 지급액
    private BigDecimal totalAmount;
    // 해지 종류
    private CloseSavingsFlexibleAccountTotal.CloseType closeType;

    // 해지 종류 Enum을 public으로 선언
    public enum CloseType {
        EARLY_TERMINATION("중도 해지"),
        MATURITY_TERMINATION("만기 해지"),
        POST_MATURITY_TERMINATION("만기 후 해지");

        private final String description;

        CloseType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // closeType의 description을 반환하는 메서드 추가
    public String getCloseTypeDescription() {
        return this.closeType != null ? this.closeType.getDescription() : null;
    }

    @Builder
    public CloseSavingsAccountTotal(String accountId, String accountStatus, String customerName, long customerId, String productName, BigDecimal accountInterestRate, BigDecimal accountBalance, String openDate, String startDate, BigDecimal balance, String expectedExpireDate, BigDecimal productInterestRate, BigDecimal productTaxRate, String productType, String productPeriod, BigDecimal fixedAmount, String autoTransferStartDate, String autoTransferEndDate, String autoTransferPeriod, String createDate, String targetAccId, String autoTransferId, String autoTransferCount, BigDecimal interestCashSum, BigDecimal totalBalanceSum, BigDecimal finalInterestRate, BigDecimal totalInterestSum, BigDecimal totalInterestSumAfterTax, BigDecimal totalAmount, CloseSavingsFlexibleAccountTotal.CloseType closeType) {
        this.accountId = accountId;
        this.accountStatus = accountStatus;
        this.customerName = customerName;
        this.customerId = customerId;
        this.productName = productName;
        this.accountInterestRate = accountInterestRate;
        this.accountBalance = accountBalance;
        this.openDate = openDate;
        this.startDate = startDate;
        this.balance = balance;
        this.expectedExpireDate = expectedExpireDate;
        this.productInterestRate = productInterestRate;
        this.productTaxRate = productTaxRate;
        this.productType = productType;
        this.productPeriod = productPeriod;
        this.fixedAmount = fixedAmount;
        this.autoTransferStartDate = autoTransferStartDate;
        this.autoTransferEndDate = autoTransferEndDate;
        this.autoTransferPeriod = autoTransferPeriod;
        this.createDate = createDate;
        this.targetAccId = targetAccId;
        this.autoTransferId = autoTransferId;
        this.autoTransferCount = autoTransferCount;
        this.interestCashSum = interestCashSum;
        this.totalBalanceSum = totalBalanceSum;
        this.finalInterestRate = finalInterestRate;
        this.totalInterestSum = totalInterestSum;
        this.totalInterestSumAfterTax = totalInterestSumAfterTax;
        this.totalAmount = totalAmount;
        this.closeType = closeType;
    }

    // 이자 합 계산
    public static CloseSavingsAccountTotal of(CloseSavingsAccountTotal closeSavingsAccountTotal) {

        BigDecimal finalInterestRate = BigDecimal.ZERO;

        // 최총 이자율
        closeSavingsAccountTotal.setFinalInterestRate(finalInterestRate);
        // 총 이자 합 (세전)
        closeSavingsAccountTotal
                .setTotalInterestSum(
                        (closeSavingsAccountTotal.getBalance())
                                .multiply(finalInterestRate)
                                .setScale(4, RoundingMode.DOWN)
                );
        // 잔액 + 이자
        closeSavingsAccountTotal
                .setTotalBalanceSum(
                        (closeSavingsAccountTotal.interestCashSum)
                        .add(closeSavingsAccountTotal.accountBalance)
                );

        // 세후 총 이자 합
        closeSavingsAccountTotal
                .setTotalInterestSumAfterTax(
                        (closeSavingsAccountTotal.totalInterestSum)
                        .multiply(closeSavingsAccountTotal.getProductTaxRate())
                        .setScale(4, RoundingMode.DOWN)
                );
       
       
        // 최종 지급액 계산 : 10의 자리 버림
        BigDecimal totalAmount = closeSavingsAccountTotal.getBalance()
                .add(closeSavingsAccountTotal.getTotalInterestSumAfterTax())
                .divide(new BigDecimal("10"), 0, RoundingMode.DOWN)
                .multiply(new BigDecimal("10"));
        closeSavingsAccountTotal.setTotalAmount(totalAmount);

        return closeSavingsAccountTotal;
    }


    @Override
    public String toString() {
        return "CloseSavingsAccountTotal{" +
                "accountId='" + accountId + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerId=" + customerId +
                ", productName='" + productName + '\'' +
                ", accountInterestRate=" + accountInterestRate +
                ", accountBalance=" + accountBalance +
                ", openDate='" + openDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", balance=" + balance +
                ", expectedExpireDate='" + expectedExpireDate + '\'' +
                ", productInterestRate=" + productInterestRate +
                ", productTaxRate=" + productTaxRate +
                ", productType='" + productType + '\'' +
                ", productPeriod='" + productPeriod + '\'' +
                ", fixedAmount=" + fixedAmount +
                ", autoTransferStartDate='" + autoTransferStartDate + '\'' +
                ", autoTransferEndDate='" + autoTransferEndDate + '\'' +
                ", autoTransferPeriod='" + autoTransferPeriod + '\'' +
                ", createDate='" + createDate + '\'' +
                ", targetAccId='" + targetAccId + '\'' +
                ", autoTransferId='" + autoTransferId + '\'' +
                ", autoTransferCount='" + autoTransferCount + '\'' +
                ", interestCashSum=" + interestCashSum +
                ", totalBalanceSum=" + totalBalanceSum +
                ", finalInterestRate=" + finalInterestRate +
                ", totalInterestSum=" + totalInterestSum +
                ", totalInterestSumAfterTax=" + totalInterestSumAfterTax +
                ", totalAmount=" + totalAmount +
                ", closeType=" + closeType +
                '}';
    }
}
