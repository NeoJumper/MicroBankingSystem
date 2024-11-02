package com.kcc.banking.domain.bulk_transfer.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransferProgress {
    private int totalCount;
    private int successCount;
    private int failureCount;

    @Builder
    public TransferProgress(int totalCount, int successCount, int failureCount) {
        this.totalCount = totalCount;
        this.successCount = successCount;
        this.failureCount = failureCount;
    }
}