package com.kcc.banking.domain.business_day.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BusinessDay {

    private String businessDate;
    private String status;
    private String isCurrentBusinessDay;

    public void toOpen(){
        this.status = "OPEN";
        this.isCurrentBusinessDay = "TRUE";
    }

    public void toClose(){
        this.status = "CLOSE";
        this.isCurrentBusinessDay = "FALSE";
    }

}
