package com.kcc.banking.domain.employee.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class BusinessDateAndBranchId {

    private Timestamp businessDate;
    private String branchId;

    @Builder
    public BusinessDateAndBranchId(Timestamp businessDate, String branchId) {
        this.businessDate = businessDate;
        this.branchId = branchId;
    }
}
