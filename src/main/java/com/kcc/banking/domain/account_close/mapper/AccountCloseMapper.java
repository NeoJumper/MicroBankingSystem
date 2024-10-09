package com.kcc.banking.domain.account_close.mapper;

import com.kcc.banking.domain.account_close.dto.request.*;
import com.kcc.banking.domain.account_close.dto.response.CloseAccount;
import com.kcc.banking.domain.account_close.dto.response.InterestSum;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Mapper
public interface AccountCloseMapper {

    int updateStatus(AccountStatus accountStatus);
    CloseAccount findCloseAccount(String accountId);
    Timestamp findExpireDateById(String id);



    int addCancelTrade(CloseTrade closeTrade);
    BigDecimal rollbackAmount(AccountIdWithExpireDate accountIdWithExpireDate);

}
