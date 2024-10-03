package com.kcc.banking.domain.trade.service;

import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;

import com.kcc.banking.domain.trade.dto.response.*;
import com.kcc.banking.domain.employee.service.EmployeeService;
import com.kcc.banking.domain.trade.dto.request.TradeCreate;
import com.kcc.banking.domain.trade.dto.response.TradeByCash;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.response.TradeCashOfPerAccount;
import com.kcc.banking.domain.trade.dto.response.TradeDetail;
import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import com.kcc.banking.domain.trade.mapper.TradeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeMapper tradeMapper;

    private final BusinessDayService businessDayService;
    private final EmployeeService employeeService;
    private final AccountService accountService;

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

    public TradeDetail createCashTrade(TradeCreate tradeCreate){

        // 거래 계좌 조회
        AccountDetail cashTradeAccount = accountService.getAccountDetail(tradeCreate.getAccId());

        // 지점 번호
        Long branchId = Long.parseLong(employeeService.getAuthData().getBranchId());
        // 행원 번호
        Long employeeId = Long.parseLong(employeeService.getAuthData().getId());
        // 등록 일자
        Timestamp tradeDate = Timestamp.valueOf(businessDayService.getCurrentBusinessDay().getBusinessDate());

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeMapper.getNextTradeNumberVal();

        // 거래 계좌 잔액 조회
        BigDecimal cashTradeAccountBalance = cashTradeAccount.getBalance();

        // 현금 거래 내역 생성
        TradeDetail tradeDetail = TradeDetail.builder()
                // 거래 계좌
                .accId(tradeCreate.getAccId())
                // 상대 계좌 : 없음
//                .targetAccId(null)
                // 거래 금액
                .amount(tradeCreate.getAmount())
                // 유형: 입금 / 출금
                .tradeType(tradeCreate.getTradeType())
                // 지점 번호
                .branchId(branchId)
                // 등록자 번호
                .registrantId(employeeId)
                // 등록 일자
                .registrationDate(tradeDate)
                // 거래 일시
                .tradeDate(tradeDate)
                // 거래 번호
                .tradeNumber(tradeNumber)
                // 비고
                //.description(null)
                // 현금 여부 : TRUE
                .cashIndicator("TRUE")
                // 거래 상태: 정상
                .status("NOR")
                // 수정일
                .modificationDate(tradeDate)
                // 수정자
                .modifierId(employeeId)
                // 버전: 1
                .version(1L)
                .build();

        // 출금일 경우
        if(tradeCreate.getTradeType().equals("WITHDRAWAL")){
            tradeDetail.setBalance(cashTradeAccountBalance.subtract(tradeCreate.getAmount()));
        }
        // 입금일 경우
        else if(tradeCreate.getTradeType().equals("DEPOSIT")){
            tradeDetail.setBalance(cashTradeAccountBalance.add(tradeCreate.getAmount()));
        }

        // 현금 거래내역 추가
        tradeMapper.createCashTrade(tradeDetail);
        // 잔액 업데이트
        tradeMapper.updateCashTradeBalance(tradeDetail);
        return tradeDetail;
    }

}
