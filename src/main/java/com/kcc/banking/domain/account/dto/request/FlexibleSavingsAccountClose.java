package com.kcc.banking.domain.account.dto.request;

import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class FlexibleSavingsAccountClose {

    private String accId;
    // totalAmount , 총 지급액
    private BigDecimal amount;
    private String status;
    private String description;
    private String tradeType;
    private String closeType;

    // 자유적금 중도 해지를 위한 이율 변동 및 금액 계산 내역
    private List<InterestDetails> interestDetailsList;

    @Builder
    public FlexibleSavingsAccountClose(String accId, BigDecimal amount, String status, String description, String tradeType, String closeType ,List<InterestDetails> interestDetailsList) {
        this.accId = accId;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.tradeType = tradeType;
        this.closeType = closeType;
        this.interestDetailsList = interestDetailsList;
    }
}
