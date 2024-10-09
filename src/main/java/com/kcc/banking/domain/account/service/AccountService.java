package com.kcc.banking.domain.account.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.request.PasswordValidation;
import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.response.*;
import com.kcc.banking.domain.account_close.dto.request.AccountStatus;
import com.kcc.banking.domain.account_close.dto.response.CloseAccount;
import com.kcc.banking.domain.common.dto.request.RegistrantNameAndInfoAndDate;
import com.kcc.banking.domain.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.kcc.banking.domain.account.mapper.AccountMapper;
//import com.kcc.banking.domain.trade.dto.request.TradeCreate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CommonService commonService;

    public RegistrantNameAndInfoAndDate getRegistrantInfo() {
        return commonService.getDateAndBranchIdAndEmpIdAndEmpName();
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
        RegistrantNameAndInfoAndDate rnid = commonService.getDateAndBranchIdAndEmpIdAndEmpName();
        accountCreate.setStartDate(Timestamp.valueOf(rnid.getTradeDate()));
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

    public int updateStatus(AccountStatus accountStatus) {
        return accountMapper.updateStatus(accountStatus);
    }

    public CloseAccount getCloseAccount(String accountId) {
        return accountMapper.findCloseAccount(accountId);
    }

    public Timestamp getExpireDateById(String accId) {
        return accountMapper.findExpireDateById(accId);
    }
}
