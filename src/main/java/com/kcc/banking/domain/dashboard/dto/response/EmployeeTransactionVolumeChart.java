package com.kcc.banking.domain.dashboard.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeTransactionVolumeChart {
    private String employeeName;  // 직원 이름
    private Long transactionCount;  // 거래 수
}
