package com.kcc.banking.domain.interest.dto.request;

import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountDetailForInterest;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class InterestCreate {

    private Long id; // sequence
    private String accId; // 대상계좌
    private BigDecimal amount; // 이자금액
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
    public InterestCreate(Long id, String accId, BigDecimal amount, BigDecimal balance,BigDecimal interestRate, BigDecimal preferentialInterestRate, String paymentStatus, String tradeNumber, String branchId, Long registrantId, String creationDate, Long version) {
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
     * @Description
     * 잔액에 대한 이자 계산
     * balance * interest / 100 / 365
     * 근데 이제 형변환을 곁들인
     *
     * 지급 상태는 N
     * branchId와 registrantId는 일단 마감 진행자의 것으로
     * 추후에 지급한 지점이나 담당자로 바꿔야한다면 변경
     */
    public static InterestCreate of (AccountDetailForInterest accountDetail, Long loginMemberId, BusinessDateAndBranchId businessDateAndBranchId, String tradeNumber){
        BigDecimal balance = accountDetail.getBalance();  // 잔액 (BigDecimal)
        BigDecimal interestRateSum = accountDetail.getInterestRate().add(accountDetail.getPreferentialInterestRate());

        // 연 이자율을 백분율로 변환하고 1일치로 나누기 (365일 기준)
        BigDecimal dailyInterest = balance
                .multiply(interestRateSum.divide(BigDecimal.valueOf(100)))  // 이자율을 백분율로 나누기
                .multiply(BigDecimal.valueOf(1.0 / 365));  // 1일치 비율 적용 (365일 기준)


        return InterestCreate.builder()
                .accId(accountDetail.getAccId())
                .amount(dailyInterest)
                .balance(balance)
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
