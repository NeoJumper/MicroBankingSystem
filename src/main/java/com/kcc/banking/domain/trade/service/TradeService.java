package com.kcc.banking.domain.trade.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.AccountOpen;
import com.kcc.banking.domain.account.dto.request.StatusWithTrade;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.trade.dto.request.*;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;

import com.kcc.banking.domain.trade.dto.response.*;
import com.kcc.banking.domain.trade.dto.response.TradeByCash;

import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import com.kcc.banking.domain.trade.mapper.TradeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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


        // 페이징 처리 객체 생성
        PageDTO pageDTO = new PageDTO(tradeSearch.getCriteria(), totalCount);


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

    /**
     * @Description
     * - 금일 계좌의 출금 거래금액 총합
     * - 이체가능 금액 계산에 사용
     */
    public BigDecimal getTransferAmountOfToday(TradeSearch tradeSearch) {
        return tradeMapper.findTransferAmountOfToday(tradeSearch);
    }

    /**
     * @Description
     *
     */
    public BigDecimal getPaidAmount(AccountIdWithExpireDate awe) {
        return tradeMapper.findPaidAmount(awe);
    }

    public Long getNextTradeNumberVal() {
        return tradeMapper.findNextTradeNumberVal();
    }

    /**
     *@param tradeNumber
     * @return List<TransferDetail>
     * 
     * @Description
     * - 거래 번호로 조회하여 여러 거래 내역 반환
     */
    public List<TransferDetail> getTradeByTradeNumber(Long tradeNumber) {
        List<TransferDetail> tradeDetails = tradeMapper.getTradeDetailsByTradeNumber(tradeNumber);
        if(tradeDetails == null || tradeDetails.isEmpty()){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TRADE_NUMBER);
        }
        return tradeDetails;
    }


    /**
     * @Description
     * - 이체 거래시 사용
     * - 입금/출금 계좌 거래 데이터 생성 및 상세내역 반환
     */
    public TransferDetail createTransferTrade(TransferTradeCreate transferTradeCreate,String customerName ,CurrentData currentData, BigDecimal afterBalance, Long tradeNumber, String tradeType) {
        // 이체 내역 생성
        TradeCreate transferTrade = TradeCreate.builder()
                .accId(transferTradeCreate.getAccId()) // 본인 계좌
                .targetAccId(transferTradeCreate.getTargetAccId())  // 상대 계좌
                .bulkTransferId(transferTradeCreate.getBulkTransferId()) // 대량 이체 번호
                .amount(transferTradeCreate.getTransferAmount())  // 이체 금액
                .balance(afterBalance)  // 이체 후 잔액
                .tradeType(tradeType)  // 유형: 입금 / 출금
                .branchId(currentData.getBranchId())  // 지점 번호
                .registrantId(currentData.getEmployeeId())  // 등록자 번호
                .tradeDate(currentData.getCurrentBusinessDate())  // 거래 일자(영업일)
                .tradeNumber(tradeNumber)  // 거래 번호
                .description(transferTradeCreate.getDescription())  // 비고
                .cashIndicator("FALSE")  // 현금 여부
                .status("NOR")  // 거래 상태: 정상
                .build();

        tradeMapper.insertTrade(transferTrade);

        return TransferDetail.builder()
                .accId(transferTradeCreate.getAccId())
                .customerName(customerName)
                .amount(transferTradeCreate.getTransferAmount())   // 이체 금액
                .balance(afterBalance)  // 이체 후 잔액
                .build();
    }
    public void createTransferFailureTrade(TransferTradeCreate transferTradeCreate, CurrentData currentData, BigDecimal afterBalance, Long tradeNumber, String tradeType) {
        // 이체 내역 생성
        TradeCreate transferTrade = TradeCreate.builder()
                .accId(transferTradeCreate.getAccId()) // 본인 계좌
                .targetAccId(transferTradeCreate.getTargetAccId())  // 상대 계좌
                .bulkTransferId(transferTradeCreate.getBulkTransferId())
                .amount(transferTradeCreate.getTransferAmount())  // 이체 금액
                .balance(afterBalance)  // 이체 후 잔액
                .tradeType(tradeType)  // 유형: 입금 / 출금
                .branchId(currentData.getBranchId())  // 지점 번호
                .registrantId(currentData.getEmployeeId())  // 등록자 번호
                .tradeDate(currentData.getCurrentBusinessDate())  // 거래 일자(영업일)
                .tradeNumber(tradeNumber)  // 거래 번호
                .failureReason(transferTradeCreate.getFailureReason())
                .description(transferTradeCreate.getDescription())  // 비고
                .cashIndicator("FALSE")  // 현금 여부
                .status("FAIL")  // 거래 상태: 정상
                .build();

        tradeMapper.insertTrade(transferTrade);
    }

    public TransferDetail createTransferCancelTrade(TransferTradeCreate transferTradeCreate, String customerName,CurrentData currentData, BigDecimal afterBalance, Long tradeNumber, String tradeType) {
        // 입금 내역 생성
        TradeCreate transferTrade = TradeCreate.builder()
                .accId(transferTradeCreate.getAccId()) // 본인 계좌
                .targetAccId(transferTradeCreate.getTargetAccId())  // 상대 계좌
                .amount(transferTradeCreate.getTransferAmount())  // 이체 금액
                .balance(afterBalance)  // 이체 후 잔액
                .tradeType(tradeType)  // 유형: 입금 / 출금
                .branchId(currentData.getBranchId())  // 지점 번호
                .registrantId(currentData.getEmployeeId())  // 등록자 번호
                .tradeDate(currentData.getCurrentBusinessDate())  // 거래 일자(영업일)
                .tradeNumber(tradeNumber)  // 거래 번호
                .description(transferTradeCreate.getDescription())  // 비고
                .cashIndicator("FALSE")  // 현금 여부
                .status("RVK")  // 거래 상태: 역거래
                .build();

        tradeMapper.insertTrade(transferTrade);

        return TransferDetail.builder()
                .accId(transferTradeCreate.getAccId())
                .customerName(customerName)
                .amount(transferTradeCreate.getTransferAmount())   // 이체 금액
                .balance(afterBalance)  // 이체 후 잔액
                .build();
    }



    /**
     * @Description
     * - 현금 거래시 사용
     * - 입출금 계좌 거래 데이터 생성 및 상세내역 반환
     */
    public TradeDetail createCashTrade(CashTradeCreate cashTradeCreate, CurrentData currentData, BigDecimal cashTradeAccountBalance, Long tradeNumber) {

        TradeCreate tradeCreate = TradeCreate.builder()
                .accId(cashTradeCreate.getAccId())
                .amount(cashTradeCreate.getAmount())  // 거래 금액
                .tradeType(cashTradeCreate.getTradeType()) // 유형: 입금 or 출금
                .branchId(currentData.getBranchId())  // 지점 번호
                .registrantId(currentData.getEmployeeId())  // 등록자 번호
                .tradeDate(currentData.getCurrentBusinessDate())  // 거래 일자(영업일)
                .tradeNumber(tradeNumber)  // 거래 번호
                //.description(null)  비고 없음(?)
                .cashIndicator("TRUE")  // 현금 여부 : TRUE
                .status("NOR") // 거래 상태: 정상
                .build();

        // 출금일 경우
        if(cashTradeCreate.getTradeType().equals("WITHDRAWAL")){
            tradeCreate.setBalance(cashTradeAccountBalance.subtract(cashTradeCreate.getAmount()));
        }
        // 입금일 경우
        else if(cashTradeCreate.getTradeType().equals("DEPOSIT")){
            tradeCreate.setBalance(cashTradeAccountBalance.add(cashTradeCreate.getAmount()));
        }

        tradeMapper.insertTrade(tradeCreate);


        return TradeDetail.of(tradeCreate);
    }

    public TradeCreate createCloseTrade(StatusWithTrade statusWithTrade, CurrentData currentData, Long tradeNumber) {
        TradeCreate tradeCreate = TradeCreate.builder()
                .accId(statusWithTrade.getAccId())
                .branchId(currentData.getBranchId())
                .amount(statusWithTrade.getAmount())
                .description(statusWithTrade.getDescription())
                .balance(BigDecimal.valueOf(0))
                .registrantId(currentData.getEmployeeId())
                .tradeType(statusWithTrade.getTradeType())
                .tradeNumber(tradeNumber)  // 거래 번호
                .cashIndicator("TRUE")  // 현금 여부 : TRUE
                .status("NOR") // 거래 상태: 정상
                .tradeDate(currentData.getCurrentBusinessDate()).build();

        tradeMapper.insertTrade(tradeCreate);
        return tradeCreate;
    }

    public int createCloseCancelTrade(String accId, CurrentData currentData, BigDecimal rollbackBalance, Long tradeNumber) {
        TradeCreate tradeCreate = TradeCreate.builder()
                .accId(accId)
                .branchId(currentData.getBranchId())
                .amount(rollbackBalance)
                .balance(rollbackBalance)
                .description("계좌해지취소")
                .registrantId(currentData.getEmployeeId())
                .tradeType("CLOSE")
                .tradeNumber(tradeNumber)  // 거래 번호
                .cashIndicator("TRUE")  // 현금 여부 : TRUE
                .status("RVK") // 거래 상태: 정상
                .tradeDate(currentData.getCurrentBusinessDate()).build();

        return tradeMapper.insertTrade(tradeCreate);
    }

    public void createOpenTrade(AccountOpen accountOpen, CurrentData currentData, Long tradeNumber) {
        TradeCreate tradeCreate = TradeCreate.builder()
                .accId(accountOpen.getId())
                .amount(accountOpen.getBalance())  // 거래 금액
                .balance(accountOpen.getBalance())  // 거래 금액
                .tradeType(accountOpen.getTradeType()) // 유형: 개설
                .branchId(currentData.getBranchId())  // 지점 번호
                .registrantId(currentData.getEmployeeId())  // 등록자 번호
                .tradeDate(currentData.getCurrentBusinessDate())  // 거래 일자(영업일)
                .tradeNumber(tradeNumber)  // 거래 번호
                //.description(null)  비고 없음(?)
                .cashIndicator("TRUE")  // 현금 여부 : TRUE
                .status("NOR") // 거래 상태: 정상
                .build();

        tradeMapper.insertTrade(tradeCreate);
    }

    public int updateTradeStatusToCancel(Long tradeNumber) {
        return tradeMapper.updateTradeStatusToCancel(tradeNumber);
    }

    public List<TradeByBulkTransfer> getTradeListByBulkTransfer(Long bulkTransferId) {
        return tradeMapper.findTradeListByBulkTransfer(bulkTransferId);
    }

    public List<TradeDetail> bulkTransferFailTradeList(Long bulkTransferId) {
        return tradeMapper.findBulkTransferFailTradeList(bulkTransferId);
    }
}



