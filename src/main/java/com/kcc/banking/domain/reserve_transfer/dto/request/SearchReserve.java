package com.kcc.banking.domain.reserve_transfer.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class SearchReserve {

    //현재 날짜
    String currenDate;

    //wait 상태인 예약이체
    private String status;
    private String transferType;

}
