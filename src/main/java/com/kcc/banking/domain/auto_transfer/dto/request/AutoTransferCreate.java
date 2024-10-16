package com.kcc.banking.domain.auto_transfer.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class AutoTransferCreate {

    // 계좌번호
    private String id;
    private String accId;  // 계좌 ID
    private String targetAccId;  // 대상 계좌 ID
    private BigDecimal amount;  // 이체 금액
    private String autoTransferStartDate;  // 자동 이체 시작일
    private String autoTransferDate;  // 자동 이체일
    private String autoTransferEndDate;  // 자동 이체 종료일
    private String autoTransferPeriod;  // 자동 이체 주기
    private String createDate;  // 생성일
    private String registrationDate;  // 등록일
    private String registrantId;  // 등록자 ID
    private String modificationDate;  // 수정일
    private String modifierId;  // 수정자 ID

    @Builder
    public AutoTransferCreate(String id, String accId, String targetAccId, BigDecimal amount, String autoTransferStartDate,
                              String autoTransferDate, String autoTransferEndDate, String autoTransferPeriod,
                              String createDate, String registrationDate, String registrantId,
                              String modificationDate, String modifierId) {
        this.id = id;
        this.accId = accId;
        this.targetAccId = targetAccId;
        this.amount = amount;
        this.autoTransferStartDate = autoTransferStartDate;
        this.autoTransferDate = autoTransferDate;
        this.autoTransferEndDate = autoTransferEndDate;
        this.autoTransferPeriod = autoTransferPeriod;
        this.createDate = createDate;
        this.registrationDate = registrationDate;
        this.registrantId = registrantId;
        this.modificationDate = modificationDate;
        this.modifierId = modifierId;
    }
}