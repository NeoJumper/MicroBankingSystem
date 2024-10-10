package com.kcc.banking.domain.account.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.*;
import com.kcc.banking.domain.account.dto.response.*;
import com.kcc.banking.domain.trade.dto.request.CloseAccount;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.kcc.banking.domain.account.mapper.AccountMapper;
//import com.kcc.banking.domain.trade.dto.request.TradeCreate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CommonService commonService;

    public CurrentData getRegistrantInfo() {
        return commonService.getCurrentData();
    }

    public void validatePassword(PasswordValidation passwordValidation) {
        String accountPassword = accountMapper.findPasswordByAccNumber(passwordValidation.getAccountNumber());

        if (!passwordEncoder.matches(passwordValidation.getPassword(), accountPassword)) {
            throw new BadRequestException(ErrorCode.INVALID_ACCOUNT_PASSWORD);
        }
    }

    public AccountProductInfo getAccountProductInfo() {
        return accountMapper.findAccountProductInfo();
    }

    /**
     * @Description
     * - 계좌 번호 생성 함수
     *   (브랜치 번호 - 계좌번호 - 랜덤 번호)
     *   (001 - 0000001 - 4256)
     */
    public String generateAccountNumber(int branchNumber, int accountSeq) {

        String formattedBranchNumber = String.format("%03d", branchNumber);
        String formattedAccountSeq = String.format("%07d", accountSeq);

        Random random = new Random();
        int randomDigit = random.nextInt(10);

        return formattedBranchNumber + "-" + formattedAccountSeq + "-" + randomDigit;
    }


    /*
     * 계좌 개설 함수
     *
     * Account insert
     * Trade insert
     *
     * */
    public void openAccount(AccountCreate accountCreate) {
        int customerSeq = accountMapper.getAccountSeq();

        // 버전 update 관리
        CurrentData rnid = commonService.getCurrentData();
        accountCreate.setStartDate(Timestamp.valueOf(rnid.getCurrentBusinessDate()));
        accountCreate.setBranchId(Integer.parseInt(String.valueOf(rnid.getBranchId())));
        accountCreate.setRegistrantId(rnid.getEmployeeId());


        int branchNumber = accountCreate.getBranchId();
        // 계좌 번호 생성
        String accountNumber = generateAccountNumber(branchNumber, customerSeq);
        accountCreate.setId(accountNumber);

        //DB에 계좌 정보 저장
//        accountMapper.openAccount(accountCreate);
//        TradeCreate tradeCreate = TradeCreate.builder()
//                .accId(accountNumber)
//                .tradeType("OPEN")
//                .amount(accountCreate.getBalance())
//                .build();
//
//        tradeService.createCashTrade(tradeCreate);

    }

    public List<AccountOfModal> getAccountsBySearchOption(SearchAccountOfModal searchAccountOfModal) {
        return accountMapper.findAccountsBySearchOption(searchAccountOfModal);
    }

    public AccountDetail getAccountDetail(String accountId) {
        return accountMapper.getAccountDetail(accountId);
    }

    public AccountOpenResultOfModal getAccountOpenResultOfModal(String accId) {
        return accountMapper.findAccountOpenResultOfModal(accId);
    }

    public List<AccountDetailForInterest> getAccountListByBranchId(Long branchId) {
        return accountMapper.findAccountByBranchId(branchId);
    }


    public CloseAccount getCloseAccount(String accountId) {
        return accountMapper.findCloseAccount(accountId);
    }

    public String getExpireDateById(String accId) {
        return accountMapper.findExpireDateById(accId);
    }

    /**
     * @Description
     * 잔액만을 변경
     * 이체 거래에 사용
     */
    public void updateByTransferTrade(AccountDetail depositAccount, CurrentData currentData, BigDecimal transferAfterAccountBalance) {
        AccountUpdate accountUpdate =  AccountUpdate.builder()
                .targetAccId(depositAccount.getId())
                .modifierId(currentData.getEmployeeId())
                .balance(transferAfterAccountBalance) // 이체 후 잔액
                .build();

        accountMapper.partialUpdateAccount(accountUpdate);
    }

    /**
     * @Description
     * 잔액과 상태를 변경
     * 해지 거래에 사용
     */
    public int updateByCloseTrade(StatusWithTrade statusWithTrade, CurrentData currentData) {
        AccountUpdate accountUpdate = AccountUpdate.builder()
                .targetAccId(statusWithTrade.getAccId())
                .status(statusWithTrade.getStatus())
                .modifierId(currentData.getEmployeeId())
                .balance(BigDecimal.valueOf(0))
                .expireDate(currentData.getCurrentBusinessDate())
                .build();

        return accountMapper.partialUpdateAccount(accountUpdate);
    }

    /**
     * @Description
     * 잔액과 상태를 변경
     * 해지 취소 거래에 사용
     */
    public int updateByCloseCancelTrade(String accId, CurrentData currentData, BigDecimal balance) {
        AccountUpdate accountUpdate = AccountUpdate.builder()
                .targetAccId(accId)
                .status("OPN")
                .modifierId(currentData.getEmployeeId())
                .balance(balance)
                //.expireDate(null)  SQL문에서 NULL값으로 직접 변경
                .build();

        return accountMapper.partialUpdateAccount(accountUpdate);
    }
    /**
     * @Description
     * 잔액만을 변경
     * 현금 거래에 사용
     */
    public int updateByCashTrade(AccountDetail cashTradeAccount, CurrentData currentData, BigDecimal afterTradeBalance) {
        AccountUpdate accountUpdate = AccountUpdate.builder()
                .targetAccId(cashTradeAccount.getId())
                .modifierId(currentData.getEmployeeId())
                .balance(afterTradeBalance) // 거래 후 잔액
                .build();
        return accountMapper.partialUpdateAccount(accountUpdate);
    }
}
