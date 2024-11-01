package com.kcc.banking.domain.interest.dto.request;

import com.kcc.banking.domain.account.dto.response.AccountDetailForInterest;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class SavingInterestCreate {
    private Long id; // sequence
    private String accId; // 대상계좌
    private BigDecimal amount; // 이자
    private BigDecimal balance; // 잔액
    private BigDecimal interestRate; // 기본이율
    private BigDecimal preferentialInterestRate; // 우대이율
    private String paymentStatus; // 지금상태
    private String tradeNumber; // 생성 거래 번호
    private String branchId; // 생성 지점
    private Long registrantId; // 생성한 사람
    private String creationDate; // 생성일
    private Long version;

    @Builder
    public SavingInterestCreate(Long id, String accId, BigDecimal amount, BigDecimal balance,BigDecimal interestRate, BigDecimal preferentialInterestRate, String paymentStatus, String tradeNumber, String branchId, Long registrantId, String creationDate, Long version) {
        this.id = id;
        this.accId = accId;
        this.amount = amount;
        this.balance = balance;
        this.interestRate = interestRate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.paymentStatus = paymentStatus;
        this.tradeNumber = tradeNumber;
        this.branchId = branchId;
        this.registrantId = registrantId;
        this.creationDate = creationDate;
        this.version = version;
    }


    /**
     * @param accountDetail
     * @param loginMemberId
     * @param businessDateAndBranchId
     * @param tradeNumber
     * @param interestSavingSum
     * @return
     * @Discription 1. 잔액에 대한 월별 이자 계산
     * 2. 단리 / 복리 계산 분기처리
     * 3. 지급 상태는 모두 N
     * 4. branchId와 registrantId는 마감 진행자인 매니저 ID
     */
    public static SavingInterestCreate of(AccountDetailForInterest accountDetail, Long loginMemberId, BusinessDateAndBranchId businessDateAndBranchId, String tradeNumber, InterestSum interestSavingSum) {
        // 잔액 및 이자율 합산
        BigDecimal balance = accountDetail.getBalance();
        // 이자율을 퍼센트에서 소수로 변환 후 합산 (2.6% -> 0.026)
        BigDecimal interestRateSum = accountDetail.getInterestRate().add(accountDetail.getPreferentialInterestRate())
                .divide(BigDecimal.valueOf(100), 8, RoundingMode.DOWN); // scale을 8로 설정

        // 월 이자율 계산 (연 이자율을 12로 나눔, 소수점 자릿수 유지)
        BigDecimal monthlyInterestRate = interestRateSum.divide(BigDecimal.valueOf(12), 8, RoundingMode.DOWN);


        // 이자 계산 (단리 또는 복리)
        BigDecimal interestAmount = null;
        if (accountDetail.getInterestCalculationMethod().equals("SIMPLE")) {
            // 단리 계산
            interestAmount = balance.multiply(monthlyInterestRate).setScale(4, RoundingMode.DOWN);
        } else if (accountDetail.getInterestCalculationMethod().equals("COMPOUND")) {
            // 1. 복리 계산
            // 1-1. 복리인데 첫 달이라면 단리와 동일하게 원금에 대한 이자 계산
            if (interestSavingSum == null) {
                interestAmount = balance.multiply(monthlyInterestRate).setScale(4, RoundingMode.DOWN);
            }
            // 1-2. 첫 달이 아니라면 원금과 이자를 합산한 금액에 대한 이자 계산
            else {
                BigDecimal balanceWithInterest = balance.add(interestSavingSum.getAmountSum());
                interestAmount = balanceWithInterest.multiply(monthlyInterestRate).setScale(4, RoundingMode.DOWN);
            }
        }

        // SavingInterestCreate 객체 생성
        return SavingInterestCreate.builder()
                .accId(accountDetail.getAccId())
                .amount(interestAmount)
                .balance(accountDetail.getBalance())
                .interestRate(accountDetail.getInterestRate())
                .preferentialInterestRate(accountDetail.getPreferentialInterestRate())
                .paymentStatus("N")
                .tradeNumber(tradeNumber)
                .branchId(businessDateAndBranchId.getBranchId())
                .registrantId(loginMemberId)
                .creationDate(businessDateAndBranchId.getBusinessDate())
                .version(1L)
                .build();
    }


}
