package com.kcc.banking.domain.account.dto.request;

import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class FixedSavingsAccountClose {

    private String accId;
    // totalAmount , 총 지급액
    private BigDecimal amount;
    private String status;
    private String description;
    private String tradeType;
    private String closeType;


    @Builder
    public FixedSavingsAccountClose(String accId, BigDecimal amount, String status, String description, String tradeType, String closeType , List<InterestDetails> interestDetailsList) {
        this.accId = accId;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.tradeType = tradeType;
        this.closeType = closeType;
    }
}
