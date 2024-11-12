package com.kcc.banking.domain.account.dto.response;

import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class CloseSavingsFlexibleAccountTotal {

    // 계좌
    private String id;
    private String openDate;
    private BigDecimal balance;
    private String status;
    private BigDecimal preferentialInterestRate;
    // 예상 만기일
    private String expectedExpireDate;

    // 고객
    private Long customerId;
    private String customerName;
    private String securityLevel;
    
    // 상품
    private String productName;
    private String productType;
    private String interestCalculationMethod;
    private String period;
    private BigDecimal interestRate;
    private BigDecimal taxRate;

    // 해지 시 최종 이율
    private BigDecimal finalInterestRate;
    // 해지 시 최종 이자 금액 합산
    private BigDecimal totalInterestSum;
    // 해지 시 최종 이자*세율 계산 금액
    private BigDecimal totalInterestSumAfterTax;
    // 해지 시 최종 지급액
    private BigDecimal totalAmount;
    // 해지 시 이자 내역
    private List<InterestDetails> interestDetailsList;
    // 해지 종류
    private CloseType closeType;

    // 해지 종류 Enum을 public으로 선언
    public enum CloseType {
        EARLY_TERMINATION("중도 해지"),

        LESS_THAN_ONE_MONTH("1개월 미만"),
        ONE_TO_THREE_MONTHS("1개월 이상 ~ 3개월 미만"),
        THREE_TO_SIX_MONTHS("3개월 이상 ~ 6개월 미만"),
        SIX_TO_NINE_MONTHS("6개월 이상 ~ 9개월 미만"),
        NINE_TO_ELEVEN_MONTHS("9개월 이상 ~ 11개월 미만"),
        MORE_THAN_ELEVEN_MONTHS("11개월 이상"),
        MATURITY_TERMINATION("만기 해지"),
        POST_MATURITY_TERMINATION("만기 후 해지"),
        OVER_ONE_MONTH_POST_MATURITY("만기 후 1개월 초과");



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
    public CloseSavingsFlexibleAccountTotal(String id, String openDate, BigDecimal balance, String status, String securityLevel, Long customerId, String customerName, String productName, String productType, String interestCalculationMethod, String period, BigDecimal interestRate, BigDecimal preferentialInterestRate) {
        this.id = id;
        this.openDate = openDate;
        this.balance = balance;
        this.status = status;
        this.securityLevel = securityLevel;
        this.customerId = customerId;
        this.customerName = customerName;
        this.productName = productName;
        this.productType = productType;
        this.interestCalculationMethod = interestCalculationMethod;
        this.period = period;
        this.interestRate = interestRate;
        this.preferentialInterestRate = preferentialInterestRate;
    }

    // 이자 합 계산
    public static CloseSavingsFlexibleAccountTotal of(CloseSavingsFlexibleAccountTotal closeSavingsFlexibleAccountTotal, List<InterestDetails> interestDetailsList) {
        BigDecimal totalInterestSum = BigDecimal.ZERO;
        for(InterestDetails interestDetail : interestDetailsList) {
            totalInterestSum = totalInterestSum.add(interestDetail.getAmount());
        }

        // 이자 합 : 세전
        closeSavingsFlexibleAccountTotal.setTotalInterestSum(totalInterestSum);

        // 세후 이자 합 계산
        // ex) 1 - 0.154
        closeSavingsFlexibleAccountTotal.setTotalInterestSumAfterTax(
                totalInterestSum.multiply(BigDecimal.ONE.subtract(closeSavingsFlexibleAccountTotal.getTaxRate()))
                        .setScale(4, RoundingMode.DOWN)
        );
        // 이자 내역 저장
        closeSavingsFlexibleAccountTotal.setInterestDetailsList(interestDetailsList);

        // 최종 지급액 계산 : 10의 자리 버림
        BigDecimal totalAmount = closeSavingsFlexibleAccountTotal.getBalance()
                .add(closeSavingsFlexibleAccountTotal.getTotalInterestSumAfterTax())
                .divide(new BigDecimal("10"), 0, RoundingMode.DOWN)
                .multiply(new BigDecimal("10"));
        closeSavingsFlexibleAccountTotal.setTotalAmount(totalAmount);

        return closeSavingsFlexibleAccountTotal;
    }
}
