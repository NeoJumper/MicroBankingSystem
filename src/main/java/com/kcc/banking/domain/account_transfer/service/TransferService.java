package com.kcc.banking.domain.account_transfer.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.PasswordValidation;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.account_transfer.dto.request.TradeCancelRequest;
import com.kcc.banking.domain.account_transfer.dto.request.TransferCreate;
import com.kcc.banking.domain.account_transfer.dto.response.TransferDetail;
import com.kcc.banking.domain.account_transfer.mapper.TransferMapper;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferMapper transferMapper;

    private final AccountService accountService;
    private final EmployeeService employeeService;
    private final BusinessDayService businessDayService;

    // 예외 발생 시 롤백을 강제
    @Transactional(rollbackFor = {Exception.class})  // 모든 예외 발생 시 롤백
    public List<TransferDetail> processTransfer(TransferCreate transferCreate) {

        // 출금 계좌 조회
        AccountDetail withdrawalAccount = accountService.getAccountDetail(transferCreate.getWithdrawalAccount());
        // 입금 계좌 조회
        AccountDetail depositAccount = accountService.getAccountDetail(transferCreate.getDepositAccount());

        // 출금 계좌 조회 시
        if(withdrawalAccount == null) {
            throw new BadRequestException(ErrorCode.NOT_FOUND_ACCOUNT);
        }
        // 출금 계좌 상태 확인
        else if(withdrawalAccount.getStatus().equals("CLS")){
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }
        // 비밀번호 검증
        else{
            accountService.validatePassword(new PasswordValidation(transferCreate.getWithdrawalAccount(), transferCreate.getWithdrawalAccountPassword()));
        }

        // 출금 계좌 잔액 조회
        BigDecimal withdrawalAccountAmount = withdrawalAccount.getBalance();
        
        // 이체 금액이 잔액보다 큰 경우
        if (withdrawalAccountAmount.subtract(transferCreate.getTransferAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException(ErrorCode.OVER_TRANSFER_AMOUNT);
        }

        // 입금 계좌 잔액
        BigDecimal depositAccountBalance = accountService.getAccountDetail(transferCreate.getDepositAccount()).getBalance();


        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = transferMapper.getNextTradeNumberVal();

        // 지점 번호
        Long branchId = Long.parseLong(employeeService.getAuthData().getBranchId());
        // 행원 번호
        Long employeeId = Long.parseLong(employeeService.getAuthData().getId());
        // 등록 일자
        Timestamp tradeDate = Timestamp.valueOf(businessDayService.getCurrentBusinessDay().getBusinessDate());

        // 출금 내역 생성
        TransferDetail withdrawalTrade = TransferDetail.builder()
                // 출금 계좌
                .accId(transferCreate.getWithdrawalAccount())
                // 상대 계좌 : 입금 계좌
                .targetAccId(transferCreate.getDepositAccount())
                // 이체 금액
                .amount(transferCreate.getTransferAmount())
                // 이체 후 잔액
                .balance(withdrawalAccountAmount.subtract(transferCreate.getTransferAmount()))
                // 유형: 출금
                .tradeType("WITHDRAWAL")
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
                .description(transferCreate.getDescription())
                // 현금 여부: FALSE
                .cashIndicator("FALSE")
                // 거래 상태: 정상
                .status("NOR")
                // 수정일
                .modificationDate(tradeDate)
                // 수정자
                .modifierId(employeeId)
                // 버전: 1
                .version(1L)
                .build();


        // 출금 거래내역 추가
        transferMapper.insertTransfer(withdrawalTrade);

        // 출금 계좌 잔액 업데이트
        transferMapper.updateAccountBalance(withdrawalTrade);


        // ---------------------------------------------------------------------------------------

        // 입금 계좌 조회 시
        if(depositAccount == null){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TARGET_ACCOUNT);
        }
        //입금 계좌 상태 확인
        else if(depositAccount.getStatus().equals("CLS")){
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }

        // 입금 내역 생성
        TransferDetail depositTrade = TransferDetail.builder()
                // 입금 계좌
                .accId(transferCreate.getDepositAccount())
                // 상대 계좌: 출금 계좌
                .targetAccId(transferCreate.getWithdrawalAccount())
                // 이체 금액
                .amount(transferCreate.getTransferAmount())
                // 이체 후 잔액
                .balance(depositAccountBalance.add(transferCreate.getTransferAmount()))
                // 유형: 입금
                .tradeType("DEPOSIT")
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
                .description(transferCreate.getDescription())
                // 현금 여부
                .cashIndicator("FALSE")
                // 거래 상태: 정상
                .status("NOR")
                // 수정일
                .modificationDate(tradeDate)
                // 수정자
                .modifierId(employeeId)
                // 버전: 1
                .version(1L)
                .build();


        // 입금 거래내역 추가
        transferMapper.insertTransfer(depositTrade);
        // 입금 계좌 잔액 업데이트
        transferMapper.updateAccountBalance(depositTrade);


        // 출금 내역과 입금 내역 반환
        return Arrays.asList(withdrawalTrade, depositTrade);

    }

    public List<TransferDetail> getTradeByTradeNumber(Long tradeNumber) {
        List<TransferDetail> tradeDetails = transferMapper.getTradeDetailsByTradeNumber(tradeNumber);
        if(tradeDetails == null || tradeDetails.isEmpty()){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TRADE_NUMBER);
        }
        return tradeDetails;
    }

    public List<TransferDetail> updateCancelTransferCAN(TradeCancelRequest tradeCancelRequest) {
        Long tradeNumber = Long.valueOf(tradeCancelRequest.getTradeNumber());
        // 업데이트 구문
        int transferUpdateCAN = transferMapper.updateCancelTransferCAN(tradeNumber);
        if(transferUpdateCAN == 0){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TRADE_NUMBER);
        }
        return getTradeByTradeNumber(tradeNumber);
    }
}
