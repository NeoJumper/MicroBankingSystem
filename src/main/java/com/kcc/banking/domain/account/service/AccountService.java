package com.kcc.banking.domain.account.service;

import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;

    public List<AccountDetail> getAccountList(){
        return accountMapper.findAll();
    }
}