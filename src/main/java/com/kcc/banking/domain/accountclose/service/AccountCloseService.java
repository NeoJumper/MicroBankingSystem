package com.kcc.banking.domain.accountclose.service;

import com.kcc.banking.domain.accountclose.dto.request.AccountStatus;
import com.kcc.banking.domain.accountclose.mapper.AccountCloseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCloseService {
    private final AccountCloseMapper accountCloseMapper;

    public AccountStatus updateStatus(AccountStatus accountStatus) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String modifier = authentication.getName();
//        accountStatus.setModifier(modifier);
        accountStatus.setModifier("김영진");

        int resultNum = accountCloseMapper.updateStatus(accountStatus);
        if (resultNum>0){
            return accountStatus;
        }
        return null;
    }

}
