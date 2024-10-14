package com.kcc.banking.domain.bulk_transfer.dto.request;

import com.kcc.banking.domain.trade.dto.response.Criteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BulkTransferSearch {


    private String accId;
    private String startDate;
    private String endDate;
    private String sortOrder;

    private Criteria criteria;

    public BulkTransferSearch(String accId, String tradeType, String startDate, String endDate, String sortOrder, Criteria criteria) {
        this.accId = accId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sortOrder = sortOrder;
        this.criteria = criteria;
    }
}
