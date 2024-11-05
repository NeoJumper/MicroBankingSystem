package com.kcc.banking.domain.common.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CurrentData {
    private Long branchId;
    private String branchName;
    private Long employeeId;
    private String employeeName;
    private String currentBusinessDate;

    @Builder
    public CurrentData(Long branchId, String branchName, Long employeeId, String employeeName, String currentBusinessDate) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.currentBusinessDate = currentBusinessDate;
    }


    public CurrentData(Long branchId,  Long employeeId,  String currentBusinessDate) {
        this.branchId = branchId;

        this.employeeId = employeeId;

        this.currentBusinessDate = currentBusinessDate;
    }

}
