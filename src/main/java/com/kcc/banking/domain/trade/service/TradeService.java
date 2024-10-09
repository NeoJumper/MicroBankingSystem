package com.kcc.banking.domain.trade.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.trade.dto.request.*;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;

import com.kcc.banking.domain.trade.dto.response.*;
import com.kcc.banking.domain.employee.service.EmployeeService;
import com.kcc.banking.domain.trade.dto.response.TradeByCash;

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

    public TradeDetail createCashTrade(CashTradeCreate cashTradeCreate){

        // 거래 계좌 조회
        AccountDetail cashTradeAccount = accountService.getAccountDetail(cashTradeCreate.getAccId());

        // 지점 번호
        Long branchId = Long.parseLong(employeeService.getAuthData().getBranchId());
        // 행원 번호
        Long employeeId = Long.parseLong(employeeService.getAuthData().getId());
        // 등록 일자
        Timestamp tradeDate = Timestamp.valueOf(businessDayService.getCurrentBusinessDay().getBusinessDate());

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeMapper.findNextTradeNumberVal();

        // 거래 계좌 잔액 조회
        BigDecimal cashTradeAccountBalance = cashTradeAccount.getBalance();

        // 현금 거래 내역 생성
        TradeDetail tradeDetail = TradeDetail.builder()
                // 거래 계좌
                .accId(cashTradeCreate.getAccId())
                // 상대 계좌 : 없음
//                .targetAccId(null)
                // 거래 금액
                .amount(cashTradeCreate.getAmount())
                // 유형: 입금 / 출금
                .tradeType(cashTradeCreate.getTradeType())
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
        if(cashTradeCreate.getTradeType().equals("WITHDRAWAL")){
            tradeDetail.setBalance(cashTradeAccountBalance.subtract(cashTradeCreate.getAmount()));
        }
        // 입금일 경우
        else if(cashTradeCreate.getTradeType().equals("DEPOSIT")){
            tradeDetail.setBalance(cashTradeAccountBalance.add(cashTradeCreate.getAmount()));
        }
        // 개설일 경우 (현금입금)
        else if(cashTradeCreate.getTradeType().equals("OPEN")){
            tradeDetail.setBalance(cashTradeAccountBalance.add(cashTradeCreate.getAmount()));
        }

        // 현금 거래내역 추가
        tradeMapper.createCashTrade(tradeDetail);
        // 잔액 업데이트
        tradeMapper.updateCashTradeBalance(tradeDetail);

        return tradeDetail;
    }

    public int addCancelTrade(CloseTrade closeTrade) {
        return tradeMapper.createCancelTrade(closeTrade);
    }

    /**
     * @Description
     *
     */
    public BigDecimal rollbackAmount(AccountIdWithExpireDate awe) {
        return tradeMapper.rollbackAmount(awe);
    }

    public Long getNextTradeNumberVal() {
        return tradeMapper.findNextTradeNumberVal();
    }

    public List<TransferDetail> getTradeByTradeNumber(Long tradeNumber) {
        List<TransferDetail> tradeDetails = tradeMapper.getTradeDetailsByTradeNumber(tradeNumber);
        if(tradeDetails == null || tradeDetails.isEmpty()){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TRADE_NUMBER);
        }
        return tradeDetails;
    }
    public List<TransferDetail> updateCancelTransferCAN(TradeCancelRequest tradeCancelRequest) {
        Long tradeNumber = Long.valueOf(tradeCancelRequest.getTradeNumber());
        // 업데이트 구문

        int transferUpdateCAN = tradeMapper.updateTradeStatusToCancel(tradeNumber);
        if(transferUpdateCAN == 0){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TRADE_NUMBER);
        }
        return getTradeByTradeNumber(tradeNumber);
    }

    /**
     * @Description
     * - 이체 거래시 사용
     * - 출금 계좌 거래 데이터 생성 및 상세내역 반환
     */
    public TransferDetail createWithdrawalTrade(TransferTradeCreate transferTradeCreate, CurrentData currentData, BigDecimal withdrawalAccountBalance, Long tradeNumber) {
        // 출금 내역 생성
        TradeCreate withdrawalTrade = TradeCreate.builder()
                .accId(transferTradeCreate.getWithdrawalAccount()) // 출금 계좌
                .targetAccId(transferTradeCreate.getDepositAccount())  // 상대 계좌 : 입금 계좌
                .amount(transferTradeCreate.getTransferAmount())   // 이체 금액
                .balance(withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()))  // 이체 후 잔액
                .tradeType("WITHDRAWAL")  // 유형: 출금
                .branchId(currentData.getBranchId())  // 지점 번호
                .registrantId(currentData.getEmployeeId())  // 등록자 번호
                .tradeDate(currentData.getCurrentBusinessDate())  // 거래 일자(영업일)
                .tradeNumber(tradeNumber)  // 거래 번호
                .description(transferTradeCreate.getDescription())  // 비고
                .cashIndicator("FALSE")  // 현금 여부: FALSE
                .status("NOR")  // 거래 상태: 정상
                .build();

        tradeMapper.insertTrade(withdrawalTrade);

        return TransferDetail.builder()
                .accId(transferTradeCreate.getWithdrawalAccount())
                .customerName(currentData.getEmployeeName())
                .amount(transferTradeCreate.getTransferAmount())   // 이체 금액
                .balance(withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()))  // 이체 후 잔액
                .build();
    }

    /**
     * @Description
     * - 이체 거래시 사용
     * - 입금 계좌 거래 데이터 생성 및 상세내역 반환
     */
    public TransferDetail createDepositTrade(TransferTradeCreate transferTradeCreate, CurrentData currentData, BigDecimal depositAccountBalance, Long tradeNumber) {
        // 입금 내역 생성
        TradeCreate depositTrade = TradeCreate.builder()
                .accId(transferTradeCreate.getDepositAccount()) // 입금 계좌
                .targetAccId(transferTradeCreate.getWithdrawalAccount())  // 상대 계좌: 출금 계좌
                .amount(transferTradeCreate.getTransferAmount())  // 이체 금액
                .balance(depositAccountBalance.add(transferTradeCreate.getTransferAmount()))  // 이체 후 잔액
                .tradeType("DEPOSIT")  // 유형: 입금
                .branchId(currentData.getBranchId())  // 지점 번호
                .registrantId(currentData.getEmployeeId())  // 등록자 번호
                .tradeDate(currentData.getCurrentBusinessDate())  // 거래 일자(영업일)
                .tradeNumber(tradeNumber)  // 거래 번호
                .description(transferTradeCreate.getDescription())  // 비고
                .cashIndicator("FALSE")  // 현금 여부
                .status("NOR")  // 거래 상태: 정상
                .build();

        tradeMapper.insertTrade(depositTrade);

        return TransferDetail.builder()
                .accId(transferTradeCreate.getWithdrawalAccount())
                .customerName(currentData.getEmployeeName())
                .amount(transferTradeCreate.getTransferAmount())   // 이체 금액
                .balance(depositAccountBalance.add(transferTradeCreate.getTransferAmount()))  // 이체 후 잔액
                .build();
    }
}



