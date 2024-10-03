package com.kcc.banking.domain.trade.dto.response;

import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageDTO {

    private int startPage;
    private int endPage;
    private boolean prev, next;

    private int total; // 검색 조건에 따른 전체 거래 데이터의 수
    private Criteria criteria; // 검색 조건이 담긴 객체

    public PageDTO(Criteria criteria, int total) {
        this.criteria = criteria;
        this.total = total;

        // 한 페이지 블록에 표시할 마지막 페이지 번호
        this.endPage = (int) (Math.ceil(criteria.getPageNum() / 10.0)) * 10;

        // 한 페이지 블록에 표시할 시작 페이지 번호
        this.startPage = this.endPage - 9;

        // 전체 데이터 기준으로 계산한 실제 마지막 페이지 번호
        int realEnd = (int) (Math.ceil((total * 1.0) / criteria.getAmount()));

        // 계산한 마지막 페이지 번호보다 실제 페이지가 적다면 endPage를 실제 페이지로 설정
        if (realEnd <= this.endPage) {
            this.endPage = realEnd;
        }

        // 이전 버튼 활성화 여부
        this.prev = this.startPage > 1;

        // 다음 버튼 활성화 여부
        this.next = this.endPage < realEnd;
    }
}

