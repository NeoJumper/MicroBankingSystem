package com.kcc.banking.domain.accountclose.mapper;

import com.kcc.banking.domain.accountclose.dto.request.AccountStatus;
import com.kcc.banking.domain.accountclose.dto.request.CloseTrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountCloseMapper {

    int updateStatus(AccountStatus accountStatus);

    int addCancelTrade(CloseTrade closeTrade);
}
