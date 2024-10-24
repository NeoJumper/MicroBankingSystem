package com.kcc.banking.domain.dashboard.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class EmployeeTransactionVolumeChart {
    private String employeeName;       // 직원 이름
    private String transactionType;    // 거래 유형
    private long transactionCount;     // 거래 수

    @Builder
    public EmployeeTransactionVolumeChart(String employeeName, String transactionType, long transactionCount) {
        this.employeeName = employeeName;
        this.transactionType = transactionType;
        this.transactionCount = transactionCount;
    }
}
