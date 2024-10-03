package com.kcc.banking.domain.trade.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

    private int pageNum;  // 현재 페이지 번호
    private int amount;   // 페이지당 항목 수

    // 기본 생성자
    public Criteria() {
        this.pageNum = 1; // 기본 페이지 번호
        this.amount = 10; // 기본 항목 수
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}