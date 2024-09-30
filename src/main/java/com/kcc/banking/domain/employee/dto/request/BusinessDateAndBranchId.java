package com.kcc.banking.domain.employee.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BusinessDateAndBranchId {

    private String businessDate;
    private String branchId;

    @Builder
    public BusinessDateAndBranchId(String businessDate, String branchId) {
        this.businessDate = businessDate;
        this.branchId = branchId;
    }
}
