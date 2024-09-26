package com.kcc.banking.domain.account.service;

import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountProductInfo;
import com.kcc.banking.domain.account.dto.response.AccountOfModal;
import com.kcc.banking.domain.account.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;


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

        return formattedBranchNumber + formattedAccountSeq + randomDigit;
    }


    // 계좌 개설 
    public void openAccount(AccountCreate accountCreate) {
        int customerSeq = accountMapper.getAccountSeq();
        // branchId 설정
        accountCreate.setBranchId(1);
        accountCreate.setEmpId(1);


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
}
