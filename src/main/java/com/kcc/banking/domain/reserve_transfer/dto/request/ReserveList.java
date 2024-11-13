package com.kcc.banking.domain.reserve_transfer.dto.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ReserveList {


    private String transferType;
    private Long id;
    private String accId;
    private String targetAccId;
    private String autoTransferId;
    private BigDecimal amount;
    private Timestamp transferDate;
    private String transferStartTime;
    private String transferEndTime;

    private String description;
    private String status;
}
