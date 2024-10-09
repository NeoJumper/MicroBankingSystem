package com.kcc.banking.domain.common.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
@Getter
public class RegistrantNameAndInfoAndDate {
    private String branchId;
    private Long employeeId;
    private String employeeName;
    private String tradeDate;

    @Builder
    public RegistrantNameAndInfoAndDate(String branchId, Long employeeId, String employeeName, String tradeDate) {
        this.branchId = branchId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.tradeDate = tradeDate;
    }

}