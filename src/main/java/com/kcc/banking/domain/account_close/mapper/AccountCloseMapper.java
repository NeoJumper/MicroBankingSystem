package com.kcc.banking.domain.account_close.mapper;

import com.kcc.banking.domain.account_close.dto.request.*;
import com.kcc.banking.domain.account_close.dto.response.CloseAccount;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Mapper
public interface AccountCloseMapper {



    int addCancelTrade(CloseTrade closeTrade);
    BigDecimal rollbackAmount(AccountIdWithExpireDate accountIdWithExpireDate);

}
