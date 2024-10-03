package com.kcc.banking.domain.trade.service;

import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;

import com.kcc.banking.domain.trade.dto.response.*;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.mapper.TradeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeMapper tradeMapper;
    private final BusinessDayService businessDayService;

    public List<TradeByCash> findTradeByCash(BusinessDateAndEmployeeId businessDateAndEmployeeId) {
        return tradeMapper.findTradeByCashList(businessDateAndEmployeeId);
    }


    public PagingInfoOfTradeList findTradeListOfAccId(TradeSearch tradeSearch) {

        // 총 거래 건수 조회 (해당 계좌에 대한 전체 거래 수)
        int totalCount = tradeMapper.getTradeCount(tradeSearch);

        System.out.println( "서비스 : totalCount "+ totalCount);
        System.out.println( "서비스 : getPageNum "+ tradeSearch.getCriteria().getPageNum());

        // 페이징 처리 객체 생성
        PageDTO pageDTO = new PageDTO(tradeSearch.getCriteria(), totalCount);

        System.out.println("pageDTO.getCriteria()"+pageDTO.getCriteria());

        // 특정 계좌에 대한 페이징 처리된 거래 리스트 조회
        List<TradeOfList> tradeList = tradeMapper.findTradeListOfAccId(tradeSearch);

        // TradeSearch에 id 값이 없으면 tradeList와 totalCount, pageDTO만 포함한 결과를 반환
        if (tradeSearch.getAccId() == null || tradeSearch.getAccId().isEmpty()) {
            return new PagingInfoOfTradeList(tradeList, totalCount, pageDTO);
        }


        // id가 있을 경우 한계좌에 대한 전체 입출금 금액 조회
        List<TradeInfoOfPerAccount> tradeInfoOfPerAccounts = tradeMapper.getTotalSumOfTradeList(tradeSearch);

        // 거래 리스트와 페이징 정보, 입출금 금액 정보를 포함한 결과를 반환
        return PagingInfoOfTradeList.of(tradeList, pageDTO, totalCount, tradeInfoOfPerAccounts);
    }


    public String getBusinessDay() {
        String getDay = businessDayService.getCurrentBusinessDay().getBusinessDate();
        System.out.println("businessDayService.getCurrentBusinessDay().getBusinessDate()"+ getDay);
        return businessDayService.getCurrentBusinessDay().getBusinessDate();
    }

}
