package com.kcc.banking.domain.account_transfer.service;

import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.mapper.AccountMapper;
import com.kcc.banking.domain.account_transfer.dto.request.TransferCreate;
import com.kcc.banking.domain.account_transfer.dto.response.TransferDetail;
import com.kcc.banking.domain.account_transfer.mapper.TransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferMapper transferMapper;
    private final AccountMapper accountMapper;

    // 예외 발생 시 롤백을 강제
    @Transactional(rollbackFor = {Exception.class})  // 모든 예외 발생 시 롤백
    public void processTransfer(TransferCreate transferCreate ) throws Exception {
        try {
            // 출금 내역 생성
            TransferDetail withdrawalTrade = new TransferDetail();
            // 출금 계좌
            withdrawalTrade.setAccId(transferCreate.getWithdrawalAccount());
            // 입금 계좌
            withdrawalTrade.setTargetAccId(transferCreate.getDepositAccount());
            // 이체 금액
            withdrawalTrade.setAmount(transferCreate.getTransferAmount());
            // 유형: 출금
            withdrawalTrade.setTradeType("WITHDRAWAL");

            BigDecimal withdrawalAccountAmount = accountMapper.findAccount(new SearchAccountOfModal(transferCreate.getWithdrawalAccount(), null)).get(0).getBalance();

            // 금액 출금
            withdrawalTrade.setBalance(withdrawalAccountAmount.subtract(transferCreate.getTransferAmount()));
            // 임시 지점 번호 1
            withdrawalTrade.setBranchId(1L);
            // 임시 등록일 현재 시간
            withdrawalTrade.setTradeDate(new Timestamp(System.currentTimeMillis()));
            // 임시 randomUUID
            withdrawalTrade.setTradeNumber(UUID.randomUUID().toString());

            // 2024-09-27 개발 진행
/*            transferMapper.insertTransfer(withdrawalTrade);

            // 입금 내역 생성
            TransferDetail depositTrade = new TransferDetail();
            depositTrade.setAccId(transferCreate.getDepositAccId());
            depositTrade.setAmount(transferCreate.getAmount());
            depositTrade.setTradeType("DEPOSIT");
            depositTrade.setBalance(transferRequest.getDepositBalance() + transferCreate.getAmount());
            depositTrade.setBranchId(transferCreate.getBranchId());
            depositTrade.setTradeDate(new Timestamp(System.currentTimeMillis()));
            depositTrade.setTradeNumber(UUID.randomUUID().toString());

            // 입금 내역 삽입
            transferMapper.insertTransfer(depositTrade);*/

        } catch (Exception e) {
            // 필요한 경우 특정 예외를 처리하거나 로그를 남길 수 있음
            System.err.println("Error during transfer: " + e.getMessage());
            throw e;  // 예외를 다시 던져 트랜잭션이 롤백되도록 함
        }
    }
}
