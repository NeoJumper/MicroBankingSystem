package com.kcc.banking.domain.auto_transfer.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class AutoTransferList {

    private String accId;                          // 출금 계좌 ID
    private String targetAccId;                    // 입금 계좌 ID
    private BigDecimal amount;                      // 이체 금액
    private String autoTransferStartDate;       // 자동이체 시작 날짜
    private int autoTransferPeriod;                 // 자동이체 주기 (예: 1개월)
    private String status;                          // 상태 (active, paused 등)
    private Long missedCount;                        // 미납 횟수

    private Long id;                                 // 자동이체 ID
    private String autoTransferEndDate;          // 자동이체 종료 날짜
    private String createDate;                    // 생성 날짜
    private String registrationDate;              // 등록 날짜
    private Long registrantId;                       // 등록자 ID
    private String modificationDate;              // 수정 날짜
    private Long modifierId;                         // 수정자 ID
    private Long version;                         // 버전
    private Timestamp nextTransferDate;


}
