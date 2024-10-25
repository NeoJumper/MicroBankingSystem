package com.kcc.banking.domain.auto_transfer.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class AutoTransferClose {

    private BigDecimal amount;
    private String autoTransferStartDate;
    private String autoTransferEndDate;
    private String autoTransferPeriod;
    private String createDate;


    public AutoTransferClose(BigDecimal amount, String autoTransferStartDate, String autoTransferEndDate, String autoTransferPeriod, String createDate) {
        this.amount = amount;
        this.autoTransferStartDate = autoTransferStartDate;
        this.autoTransferEndDate = autoTransferEndDate;
        this.autoTransferPeriod = autoTransferPeriod;
        this.createDate = createDate;
    }
}
