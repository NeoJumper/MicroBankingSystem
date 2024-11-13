package com.kcc.banking.domain.common.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CurrentData {
    private Long branchId;
    private String branchName;
    private Long employeeId;
    private String employeeName;
    private Timestamp currentBusinessDate;

    @Builder
    public CurrentData(Long branchId, String branchName, Long employeeId, String employeeName, Timestamp currentBusinessDate) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.currentBusinessDate = currentBusinessDate;
    }


    public CurrentData(Long branchId, Long employeeId, Timestamp currentBusinessDate) {
        this.branchId = branchId;

        this.employeeId = employeeId;

        this.currentBusinessDate = currentBusinessDate;
    }

}
