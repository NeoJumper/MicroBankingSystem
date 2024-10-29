package com.kcc.banking.domain.interest.dto.request;

import com.kcc.banking.domain.account.dto.response.AccountDetailForInterest;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class SavingInterestCreate {
    private Long id; // sequence
    private String accId; // 대상계좌
    private BigDecimal amount; // 금액
    private BigDecimal interestRate; // 기본이율
    private BigDecimal preferentialInterestRate; // 우대이율
    private String paymentStatus; // 지금상태
    private String tradeNumber; // 생성 거래 번호
    private String branchId; // 생성 지점
    private Long registrantId; // 생성한 사람
    private String creationDate; // 생성일
    private Long version;

    @Builder
    public SavingInterestCreate(Long id, String accId, BigDecimal amount, BigDecimal interestRate, BigDecimal preferentialInterestRate, String paymentStatus, String tradeNumber, String branchId, Long registrantId, String creationDate, Long version) {
        this.id = id;
        this.accId = accId;
        this.amount = amount;
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
     * @Discription
     * 1. 잔액에 대한 월별 이자 계산
     * 2. 단리 / 복리 계산 분기처리
     * 3. 지급 상태는 모두 N
     * 4. branchId와 registrantId는 마감 진행자인 매니저 ID
     *
     * @param accountDetail
     * @param loginMemberId
     * @param businessDateAndBranchId
     * @param tradeNumber
     * @return
     */
    public static SavingInterestCreate of (AccountDetailForInterest accountDetail, Long loginMemberId, BusinessDateAndBranchId businessDateAndBranchId, String tradeNumber){
        return null;
    }
}
