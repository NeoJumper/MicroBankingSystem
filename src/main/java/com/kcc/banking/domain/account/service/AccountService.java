package com.kcc.banking.domain.account.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.request.PasswordValidation;
import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.response.*;
import com.kcc.banking.domain.account.mapper.AccountMapper;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.common.dto.request.RegistrantNameAndInfoAndDate;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CommonService commonService;

    public RegistrantNameAndInfoAndDate getRegistarntInfo(){
        return commonService.getDateAndBranchIdAndEmpIdAndEmpName();
    }

    public void validatePassword(PasswordValidation passwordValidation) {
        String accountPassword = accountMapper.findPasswordByAccNumber(passwordValidation.getAccountNumber());

        if(!passwordEncoder.matches(passwordValidation.getPassword(), accountPassword)){
            throw new BadRequestException(ErrorCode.INVALID_ACCOUNT_PASSWORD);
        }
    }

    public List<AccountDetail> getAccountList(){
        return accountMapper.findAll();
    }

    public AccountProductInfo getAccountProductInfo(){
        return accountMapper.findAccountProductInfo();
    }

    // 계좌 번호 생성 함수
    public String generateAccountNumber(int branchNumber, int accountSeq) {

        String formattedBranchNumber = String.format("%03d", branchNumber);
        String formattedAccountSeq = String.format("%07d", accountSeq);
        Random random = new Random();
        int randomDigit = random.nextInt(10);

        return formattedBranchNumber + "-" + formattedAccountSeq + "-" + randomDigit;
    }


    // 계좌 개설 
    public void openAccount(AccountCreate accountCreate) {
        int customerSeq = accountMapper.getAccountSeq();

        RegistrantNameAndInfoAndDate rnid = commonService.getDateAndBranchIdAndEmpIdAndEmpName();
//        yyyy-MM-dd'T'HH:mm:ss.SSSX
        System.out.println(Timestamp.valueOf(rnid.getTradeDate())+"Timestamp.valueOf(rnid.getTradeDate())");
//        // branchId 설정
        accountCreate.setStartDate(Timestamp.valueOf(rnid.getTradeDate()));
        accountCreate.setBranchId(Integer.parseInt(rnid.getBranchId()));
        accountCreate.setRegistrantId(rnid.getEmployeeId());

        
        int branchNumber = accountCreate.getBranchId();

        String accountNumber = generateAccountNumber(branchNumber, customerSeq);
        // 계좌번호 생성 입력
        accountCreate.setId(accountNumber);


        System.out.println("accountCreate.getPreferentialInterestRate();"+accountCreate.getPreferentialInterestRate());
       //DB에 계좌 정보 저장
        accountMapper.openAccount(accountCreate);
    }

    public List<AccountOfModal> getAccount(SearchAccountOfModal searchAccountOfModal) {
        return accountMapper.findAccount(searchAccountOfModal);
    }

    public AccountDetail getAccountDetail(String accountId) {
        return accountMapper.getAccountDetail(accountId);
    }

    public AccountOpenResultOfModal getAccountOpenResultOfModal(String accId){
        return accountMapper.getAccountOpenResultOfModal(accId);
    }
    public List<AccountDetailForInterest> getAccountListByBranchId(Long branchId)
    {
        return accountMapper.findAccountByBranchId(branchId);
    }
}
