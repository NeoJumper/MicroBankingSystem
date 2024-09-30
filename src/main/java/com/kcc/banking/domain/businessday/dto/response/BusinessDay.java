package com.kcc.banking.domain.businessday.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BusinessDay {

    private String businessDate;
    private String status;
    private String isCurrentBusinessDay;
}
