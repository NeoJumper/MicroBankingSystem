package com.kcc.banking.domain.trade.dto.request;

import com.kcc.banking.domain.trade.dto.response.Criteria;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TradeSearch {


    private String accId;
    private String tradeType;
    private Timestamp tradeDate;
    private Timestamp startDate;
    private Timestamp endDate;
    private String sortOrder;

    private Criteria criteria;


    @Builder
    public TradeSearch(String accId, String tradeType, Timestamp tradeDate, Timestamp startDate, Timestamp endDate, String sortOrder, Criteria criteria) {
        this.accId = accId;
        this.tradeType = tradeType;
        this.tradeDate = tradeDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sortOrder = sortOrder;
        this.criteria = criteria;
    }
}
