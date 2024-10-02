package com.kcc.banking.domain.interest.dto.request;

import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountDetailForInterest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class InterestCreate {

    private Long id;
    private String accId;
    private Long registrantId;
    private Long branchId;
    private String paymentDate;
    private BigDecimal amount;
    private float interestRate;
    private String paymentStatus;
    private String tradeNumber;

    @Builder
    public InterestCreate(Long id, String accId, Long registrantId,Long branchId, String paymentDate, BigDecimal amount, float interestRate, String paymentStatus, String tradeNumber) {
        this.id = id;
        this.accId = accId;
        this.registrantId = registrantId;
        this.branchId = branchId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.interestRate = interestRate;
        this.paymentStatus = paymentStatus;
        this.tradeNumber = tradeNumber;

    }

    public static InterestCreate of (AccountDetailForInterest accountDetail, Long loginMemberId, String paymentDate, String tradeNumber){
        BigDecimal balance = accountDetail.getBalance();  // 잔액 (BigDecimal)
        BigDecimal interestRate = BigDecimal.valueOf(accountDetail.getInterestRate());  // 연 이자율 (float을 BigDecimal로 변환)

        // 연 이자율을 백분율로 변환하고 1일치로 나누기 (365일 기준)
        BigDecimal dailyInterest = balance
                .multiply(interestRate.divide(BigDecimal.valueOf(100)))  // 이자율을 백분율로 나누기
                .multiply(BigDecimal.valueOf(1.0 / 365));  // 1일치 비율 적용 (365일 기준)


        return InterestCreate.builder()
                .accId(accountDetail.getAccId())
                .registrantId(loginMemberId)
                .branchId(accountDetail.getBranchId())
                .paymentDate(paymentDate)
                .amount(dailyInterest )
                .interestRate(accountDetail.getInterestRate())
                .paymentStatus("Y")
                .tradeNumber(tradeNumber)
                .build();
    }
}
