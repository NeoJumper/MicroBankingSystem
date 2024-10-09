package com.kcc.banking.domain.trade.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.AccountBalanceUpdate;
import com.kcc.banking.domain.account.dto.request.AccountStatus;
import com.kcc.banking.domain.account.dto.request.PasswordValidation;
import com.kcc.banking.domain.account.dto.request.StatusWithTrade;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.interest.dto.request.PaymentStatus;
import com.kcc.banking.domain.interest.dto.request.RollbackPaymentStatus;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import com.kcc.banking.domain.interest.service.InterestService;
import com.kcc.banking.domain.trade.dto.request.*;
import com.kcc.banking.domain.trade.dto.response.TradeDetail;
import com.kcc.banking.domain.trade.dto.response.TransferDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccountTradeFacade {
    private final InterestService interestService;
    private final AccountService accountService;
    private final TradeService tradeService;
    private final CommonService commonService;

    //계좌해지신청
    @Transactional(rollbackFor = Exception.class)
    public String addCloseTrade(StatusWithTrade statusWithTrade) {
        StringBuilder resultText = new StringBuilder("SUCCESS");

        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }



        //  CloseTrade, AccountStatus에 분배
        CloseTrade closeTrade = CloseTrade.builder()
                .accId(statusWithTrade.getAccId())
                .registrantId(currentData.getEmployeeId())
                .branchId(currentData.getBranchId())
                .amount(statusWithTrade.getAmount())
                .description(statusWithTrade.getDescription())
                .balance(statusWithTrade.getBalance())
                .tradeType(statusWithTrade.getTradeType())
                .businessDay(currentData.getCurrentBusinessDate()).build();

        AccountStatus accountStatus = AccountStatus.builder()
                .id(statusWithTrade.getAccId())
                .status(statusWithTrade.getStatus())
                .modifierId(currentData.getEmployeeId())
                .balance(statusWithTrade.getBalance())
                .businessDay(Timestamp.valueOf(currentData.getCurrentBusinessDate())).build();

        PaymentStatus paymentStatus = PaymentStatus.builder()
                .branchId(currentData.getBranchId())
                .payDate(Timestamp.valueOf(currentData.getCurrentBusinessDate()))
                .modifierId(currentData.getEmployeeId())
                .accId(statusWithTrade.getAccId()).build();


        int tradeResult = tradeService.addCancelTrade(closeTrade);
        int statusResult = accountService.updateStatus(accountStatus);
        int paymentStatusResult = interestService.updatePaymentStatus(paymentStatus);
        if (tradeResult > 0) {
            resultText.append(" 'tradeResult' ");
        }
        if (statusResult > 0) {
            resultText.append(" 'statusResult' ");
        }
        if (paymentStatusResult > 0) {
            resultText.append(" 'paymentStatusResult' ");
        }
        return (tradeResult + statusResult + paymentStatusResult) > 0 ? resultText.toString() : "FAIL";
    }

    public CloseAccountTotal findCloseAccountTotal(String accountId) {

        InterestSum interestSum = interestService.getInterestSum(accountId);


        Optional<CloseAccount> optCloseAccount = Optional.ofNullable(accountService.getCloseAccount(accountId));
        CloseAccount closeAccount = optCloseAccount.orElse(new CloseAccount());

        CloseAccountTotal cat = CloseAccountTotal.builder()
                .accountId(closeAccount.getAccountId())
                .accountStatus(closeAccount.getAccountStatus())
                .customerName(closeAccount.getCustomerName())
                .customerId(closeAccount.getCustomerId())
                .productName(closeAccount.getProductName())
                .amountDate(closeAccount.getAmountDate())
                .accountPreInterRate(closeAccount.getAccountPreInterRate())
                .productInterRate(closeAccount.getProductInterRate())
                .accountBal(closeAccount.getAccountBal())
                .productTaxRate(closeAccount.getProductTaxRate())
                .amountSum(interestSum.getAmountSum()).build();

        return cat;
    }

    //    해지 취소 되돌리기
    @Transactional(rollbackFor = Exception.class)
    public String rollbackAccountCancel(String accId){
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 계좌 해지 날짜
        Timestamp expireDate = accountService.getExpireDateById(accId);

        System.out.println("expireDate ===================!! 컨트롤러 안에서 해지일 " + expireDate);

        CurrentData currentData = commonService.getCurrentData();

        AccountIdWithExpireDate awe = AccountIdWithExpireDate.builder()
                .expireDate(expireDate)
                .accountId(accId).build();


        CloseAccount closeAccount = Optional.ofNullable(accountService.getCloseAccount(accId)).orElse(new CloseAccount());

        // 세율
        BigDecimal productTaxRate = closeAccount.getProductTaxRate();
        // 총 이자액
//         BigDecimal amountSum = Optional.ofNullable(accountCloseMapper.rollbackInterestSum(awe).getAmountSum()).orElse(new BigDecimal("0"));

        InterestSum rollbackInterestSum = interestService.getRollbackInterestSum(awe);
        BigDecimal amountSum = rollbackInterestSum == null ? new BigDecimal("0") : rollbackInterestSum.getAmountSum();

        // 세후이자 + 잔액
        BigDecimal rollbackAmount = Optional.ofNullable(tradeService.rollbackAmount(awe)).orElse(new BigDecimal("0"));
        // 잔액 == (거래테이블총액) - (총 이자합)*(1-세율)
        // (총 이자합) * (1 - 세율) 계산
        BigDecimal interestAfterTax = amountSum.multiply(BigDecimal.ONE.subtract(productTaxRate));
        // 잔액 계산: rollbackAmount - (총 이자합 * (1 - 세율))
        BigDecimal balance = rollbackAmount.subtract(interestAfterTax);

        // 이자 테이블 업데이트 파라미터 타입
        RollbackPaymentStatus rollbackPaymentStatus = RollbackPaymentStatus.builder()
                .branchId(currentData.getBranchId())
                .modifierId(currentData.getEmployeeId())
                .accId(accId)
                .expireDate(expireDate).build();

        // 계좌 업데이트 파라미터 타입
        AccountStatus accountStatus = AccountStatus.builder()
                .id(accId)
                .status("OPN")
                .modifierId(currentData.getEmployeeId())
                .balance(balance).build();

        CloseTrade closeTrade = CloseTrade.builder()
                .accId(accId)
                .registrantId(currentData.getEmployeeId())
                .branchId(currentData.getBranchId())
                .amount(new BigDecimal("0"))
                .description("계좌해지취소")
                .balance(balance)
                .tradeType("OPEN")
                .businessDay(currentData.getCurrentBusinessDate()).build();

        //계좌상테 변경
        int resultAccount = accountService.updateStatus(accountStatus);
        // 이자 테이블 상태변경 rollbackPaymentStatus 사용
        int resultInterest = interestService.rollbackPaymentStatus(rollbackPaymentStatus);
        // 해지 취소 거래 등록 addCancelTrade
        int resultTrade = tradeService.addCancelTrade(closeTrade);

        // 예외처리문

        return "SUCCESS";
    }


    /**
     * @Description
     * 1. 입출금 계좌 조회
     * 2. 예외 처리
     *      - 출금 계좌가 없을 때
     *      - 출금 계좌가 해지됐을 때
     *      - 비밀번호 검증에 실패했을 때
     * 3. 출금 계좌 잔액 조회
     * 4. 예외 처리
     *      - 이체 금액이 잔액보다 큰 경우
     * 5. 상대 계좌 조회 -> 입금 계좌 잔액 조회
     * 6. 거래번호 조회
     * 7. 입금 거래내역 추가 + 계좌 잔액 변경
     * 8. 출금 거래내역 추가 + 계좌 잔액 변경
     * 9. 상세보기 모달을 위한 입출금 결과 데이터 반환
     *
     */
    @Transactional(rollbackFor = {Exception.class})  // 모든 예외 발생 시 롤백
    public List<TransferDetail> processTransfer(TransferTradeCreate transferTradeCreate) {
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 입출금 계좌 조회
        AccountDetail depositAccount = accountService.getAccountDetail(transferTradeCreate.getDepositAccount());
        AccountDetail withdrawalAccount = accountService.getAccountDetail(transferTradeCreate.getWithdrawalAccount());


        if(withdrawalAccount == null) {  // 출금 계좌가 없을 때
            throw new BadRequestException(ErrorCode.NOT_FOUND_ACCOUNT);
        }
        else if(withdrawalAccount.getStatus().equals("CLS")){  // 출금 계좌가 해지됐을 때
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }
        else{   // 비밀번호 검증에 실패했을 때
            accountService.validatePassword(new PasswordValidation(transferTradeCreate.getWithdrawalAccount(), transferTradeCreate.getWithdrawalAccountPassword()));
        }

        // 출금 계좌 잔액 조회
        BigDecimal withdrawalAccountBalance = withdrawalAccount.getBalance();

        // 이체 금액이 잔액보다 큰 경우
        if (withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException(ErrorCode.OVER_TRANSFER_AMOUNT);
        }

        // 상대 계좌 조회 -> 입금 계좌 잔액 조회
        BigDecimal depositAccountBalance = accountService.getAccountDetail(transferTradeCreate.getDepositAccount()).getBalance();


        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();


        // 출금 거래내역 추가
        TransferDetail withdrawalTrade = tradeService.createWithdrawalTrade(transferTradeCreate, currentData, withdrawalAccountBalance, tradeNumber);

        // 출금 계좌 잔액 업데이트
        accountService.updateAccountBalance(AccountBalanceUpdate.builder()
                .targetAccId(withdrawalAccount.getId())
                .modifierId(currentData.getEmployeeId())
                .balance(withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount())) // 이체 후 잔액
                .build()
        );

        // 입금 계좌 조회 시
        if(depositAccount == null){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TARGET_ACCOUNT);
        }
        //입금 계좌 상태 확인
        else if(depositAccount.getStatus().equals("CLS")){
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }

        // 입금 거래내역 추가
        TransferDetail depositTrade = tradeService.createDepositTrade(transferTradeCreate, currentData, depositAccountBalance, tradeNumber);
        // 입금 계좌 잔액 업데이트
        accountService.updateAccountBalance(AccountBalanceUpdate.builder()
                .targetAccId(depositAccount.getId())
                .modifierId(currentData.getEmployeeId())
                .balance(depositAccountBalance.add(transferTradeCreate.getTransferAmount())) // 이체 후 잔액
                .build()
        );


        // 출금 내역과 입금 내역 반환
        return Arrays.asList(withdrawalTrade, depositTrade);
    }


    /**
     * @Description
     * 1. 거래 계좌 조회 + 거래 계좌 잔액 조회
     * 2. 거래번호 조회
     * 3. 거래내역 추가 + 계좌 잔액 변경
     * 4. 상세보기 모달을 위한 거래내역 결과 데이터 반환
     */
    public TradeDetail createCashTrade(CashTradeCreate cashTradeCreate){
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 거래 계좌 조회 + 거래 계좌 잔액 조회
        AccountDetail cashTradeAccount = accountService.getAccountDetail(cashTradeCreate.getAccId());
        BigDecimal cashTradeAccountBalance = cashTradeAccount.getBalance();

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();


        // 현금 거래 내역 생성
        TradeDetail tradeDetail = tradeService.createCashTrade(cashTradeCreate, currentData, cashTradeAccountBalance ,tradeNumber);

        // 잔액 업데이트
        accountService.updateAccountBalance(AccountBalanceUpdate.builder()
                .targetAccId(cashTradeAccount.getId())
                .modifierId(currentData.getEmployeeId())
                .balance(tradeDetail.getBalance()) // 거래 후 잔액
                .build()
        );

        return tradeDetail;
    }

}
