package com.kcc.banking.domain.account_transfer.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.mapper.AccountMapper;
import com.kcc.banking.domain.account_transfer.dto.request.TransferCreate;
import com.kcc.banking.domain.account_transfer.dto.response.TransferDetail;
import com.kcc.banking.domain.account_transfer.mapper.TransferMapper;
import com.kcc.banking.domain.employee.dto.response.AuthData;
import com.kcc.banking.domain.employee.mapper.EmployeeMapper;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferMapper transferMapper;
    private final AccountMapper accountMapper;

    private final EmployeeService employeeService;

    // 예외 발생 시 롤백을 강제
    @Transactional(rollbackFor = {Exception.class})  // 모든 예외 발생 시 롤백
    public List<TransferDetail> processTransfer(TransferCreate transferCreate ) throws Exception {
        try {
            // 거래번호 생성
            String tradeNumber = UUID.randomUUID().toString();

            long branchId = Long.parseLong(employeeService.getAuthData().getBranchId());
            String employeeName = employeeService.getAuthData().getName();

            // 출금 내역 생성
            TransferDetail withdrawalTrade = TransferDetail.builder()
                    // 출금 계좌
                    .accId(transferCreate.getWithdrawalAccount())
                    // 상대 계좌 : 입금 계좌
                    .targetAccId(transferCreate.getDepositAccount())
                    // 이체 금액
                    .amount(transferCreate.getTransferAmount())
                    // 유형: 출금
                    .tradeType("WITHDRAWAL")
                    //
                    .branchId()
                    // 거래 일시
                    .build();

            BigDecimal withdrawalAccountAmount = accountMapper.findAccount(new SearchAccountOfModal(transferCreate.getWithdrawalAccount(), null)).get(0).getBalance();

            // 금액 출금 후 잔액
            withdrawalTrade.setBalance(withdrawalAccountAmount.subtract(transferCreate.getTransferAmount()));
            // 임시 지점 번호 1
            withdrawalTrade.setBranchId(1L);
            // 거래 일시
            withdrawalTrade.setTradeDate(new Timestamp(System.currentTimeMillis()));
            // 등록 일시
            withdrawalTrade.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
            // 거래 번호 (입금 내역과 동일)
            withdrawalTrade.setTradeNumber(tradeNumber);
            // 등록자 ID (예: 1L, 실제로는 인증된 사용자 정보)
            withdrawalTrade.setRegistrantId(1L);

            // 비고
            withdrawalTrade.setDescription(transferCreate.getDescription());

            // 현금 여부
            withdrawalTrade.setCashIndicator("FALSE");

            // 거래 상태 여부
            withdrawalTrade.setStatus("NOR");

            // 업데이트 날짜
            withdrawalTrade.setModificationDate(new Timestamp(System.currentTimeMillis()));

            // 업데이트 등록자
            withdrawalTrade.setModifierId(1L);

            // 버전
            withdrawalTrade.setVersion(1L);

            // 출금 거래내역 추가
            transferMapper.insertTransfer(withdrawalTrade);

            // 출금 계좌 잔액 업데이트
            transferMapper.updateAccountBalance(withdrawalTrade);

            // 입금 내역 생성
            TransferDetail depositTrade = new TransferDetail();
            // 입금 계좌
            depositTrade.setAccId(transferCreate.getDepositAccount());
            // 출금 계좌
            depositTrade.setTargetAccId(transferCreate.getWithdrawalAccount());
            // 이체 금액
            depositTrade.setAmount(transferCreate.getTransferAmount());
            // 유형: 입금
            depositTrade.setTradeType("DEPOSIT");
                
            // 현재 잔액 불러오기
            BigDecimal depositAccountAmount = accountMapper.findAccount(new SearchAccountOfModal(transferCreate.getDepositAccount(), null)).get(0).getBalance();

            // 금액 입금 후 잔액
            depositTrade.setBalance(depositAccountAmount.add(transferCreate.getTransferAmount()));
            // 임시 지점 번호 1
            depositTrade.setBranchId(1L);
            // 거래 일시
            depositTrade.setTradeDate(new Timestamp(System.currentTimeMillis()));
            // 등록 일시
            depositTrade.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
            // 거래 번호 (출금 내역과 동일)
            depositTrade.setTradeNumber(tradeNumber);
            // 등록자 ID (예: 1L)
            depositTrade.setRegistrantId(1L);

            depositTrade.setDescription(transferCreate.getDescription());

            depositTrade.setCashIndicator("FALSE");

            depositTrade.setStatus("NOR");

            depositTrade.setVersion(1L);

            // 업데이트 날짜
            depositTrade.setModificationDate(new Timestamp(System.currentTimeMillis()));

            // 업데이트 등록자
            depositTrade.setModifierId(1L);
            // 버전
            depositTrade.setVersion(1L);


            // 입금 거래내역 추가
            transferMapper.insertTransfer(depositTrade);

            // 입금 계좌 잔액 업데이트
            transferMapper.updateAccountBalance(depositTrade);


            // 출금 내역과 입금 내역 반환
            return Arrays.asList(withdrawalTrade, depositTrade);

        } catch (Exception e) {
            // 예외 메시지 로그 출력
            System.err.println("Error during transfer: " + e.getMessage());
            e.printStackTrace();  // 예외 전체 출력
            throw e;
        }
    }
}
