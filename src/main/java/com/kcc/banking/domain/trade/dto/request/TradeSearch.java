package com.kcc.banking.domain.trade.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kcc.banking.domain.trade.dto.response.Criteria;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class TradeSearch {


    private String accId;
    private String tradeType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tradeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String sortOrder;

    private Criteria criteria;


    @Builder
    public TradeSearch(String accId, String tradeType, Date tradeDate, Date startDate, Date endDate, String sortOrder, Criteria criteria) {
        this.accId = accId;
        this.tradeType = tradeType;
        this.tradeDate = tradeDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sortOrder = sortOrder;
        this.criteria = criteria;
    }
}
