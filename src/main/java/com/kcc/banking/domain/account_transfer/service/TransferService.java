package com.kcc.banking.domain.account_transfer.service;

import ch.qos.logback.core.spi.ErrorCodes;
import com.kcc.banking.common.exception.CustomException;
import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountOfModal;
import com.kcc.banking.domain.account.mapper.AccountMapper;
import com.kcc.banking.domain.account_transfer.dto.request.TransferCreate;
import com.kcc.banking.domain.account_transfer.dto.response.TransferDetail;
import com.kcc.banking.domain.account_transfer.mapper.TransferMapper;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final AccountMapper accountMapper;

    private final EmployeeService employeeService;
    private final BusinessDayService businessDayService;

    // 예외 발생 시 롤백을 강제
    @Transactional(rollbackFor = {Exception.class})  // 모든 예외 발생 시 롤백
    public List<TransferDetail> processTransfer(TransferCreate transferCreate) {

        // 출금 계좌 조회
        AccountDetail withdrawalAccount = accountMapper.getAccountDetail(transferCreate.getWithdrawalAccount());
        // 입금 계좌 조회
        AccountDetail depositAccount = accountMapper.getAccountDetail(transferCreate.getDepositAccount());

        // 출금 계좌 조회 시
        if(withdrawalAccount == null) {
            throw new BadRequestException(ErrorCode.NOT_FOUND_ACCOUNT);
        }
        // 출금 계좌 상태 확인
        else if(withdrawalAccount.getStatus().equals("CLS")){
            throw new BadRequestException(ErrorCode.CLS_ACCOUNT);
        }
        // 입금 계좌 조회 시
        else if(depositAccount == null){
            throw new BadRequestException(ErrorCode.NOT_FOUND_DEPOSIT_ACCOUNT);
        }
        //입금 계좌 상태 확인
        else if(depositAccount.getStatus().equals("CLS")){
            throw new BadRequestException(ErrorCode.CLS_ACCOUNT);
        }

        // 출금 계좌 잔액 조회
        BigDecimal withdrawalAccountAmount = withdrawalAccount.getBalance();
        
        // 이체 금액이 잔액보다 큰 경우
        if (withdrawalAccountAmount.subtract(transferCreate.getTransferAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException(ErrorCode.OVER_AMOUNT);
        }

        // 입금 계좌
        BigDecimal depositAccountAmount = accountMapper.findAccount(new SearchAccountOfModal(transferCreate.getDepositAccount(), null)).get(0).getBalance();


        // 거래번호 조회 (trade_num_seq)
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

        // 입금 내역 생성
        TransferDetail depositTrade = TransferDetail.builder()
                // 입금 계좌
                .accId(transferCreate.getDepositAccount())
                // 상대 계좌: 출금 계좌
                .targetAccId(transferCreate.getWithdrawalAccount())
                // 이체 금액
                .amount(transferCreate.getTransferAmount())
                // 이체 후 잔액
                .balance(depositAccountAmount.add(transferCreate.getTransferAmount()))
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
}
