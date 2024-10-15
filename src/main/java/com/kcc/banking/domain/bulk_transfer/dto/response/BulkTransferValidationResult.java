package com.kcc.banking.domain.bulk_transfer.dto.response;


import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferValidation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BulkTransferValidationResult {

    private List<BulkTransferValidation> bulkTransferValidationList;
    private Integer totalCnt;
    private Integer normalCnt;
    private Integer inconsistencyCnt;
    private Integer errorCnt;

    @Builder
    public BulkTransferValidationResult(List<BulkTransferValidation> bulkTransferValidationList, Integer totalCnt,Integer normalCnt, Integer inconsistencyCnt, Integer errorCnt) {
        this.bulkTransferValidationList = bulkTransferValidationList;
        this.totalCnt = totalCnt;
        this.normalCnt = normalCnt;
        this.inconsistencyCnt = inconsistencyCnt;
        this.errorCnt = errorCnt;
    }
}