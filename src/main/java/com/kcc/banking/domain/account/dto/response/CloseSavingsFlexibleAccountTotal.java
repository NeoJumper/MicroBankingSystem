package com.kcc.banking.domain.account.dto.response;

import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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

    // 해지 시 최종 이율
    private BigDecimal finalInterestRate;
    // 해지 시 최종 이자 금액 합산
    private BigDecimal totalInterestSum;
    // 해지 시 최종 지급액
    private BigDecimal totalAmount;
    // 해지 시 이자 내역
    private List<InterestDetails> interestDetailsList;
    // 해지 종류
    private CloseType closeType;  // CloseType 필드 추가

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
        BigDecimal totalAmount = closeSavingsFlexibleAccountTotal.getBalance().add(totalInterestSum);

        // 계산된 값 저장
        closeSavingsFlexibleAccountTotal.setTotalAmount(totalAmount);
        closeSavingsFlexibleAccountTotal.setTotalInterestSum(totalInterestSum);
        closeSavingsFlexibleAccountTotal.setInterestDetailsList(interestDetailsList);
        return closeSavingsFlexibleAccountTotal;
    }
}
