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

    int addCancelTrade(CloseTrade closeTrade);

    InterestSum findInterestSum(String accountId);

    CloseAccount findCloseAccount(String accountId);

    int updatePaymentStatus(PaymentStatus paymentStatus);

    Timestamp findExpireDateById(String id);

    int rollbackPaymentStatus(RollbackPaymentStatus rollbackPaymentStatus);

    InterestSum rollbackInterestSum(AccountIdWithExpireDate accountIdWithExpireDate);

    BigDecimal rollbackAmount(AccountIdWithExpireDate accountIdWithExpireDate);

}
