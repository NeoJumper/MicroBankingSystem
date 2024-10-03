package com.kcc.banking.domain.account_close.mapper;

import com.kcc.banking.domain.account_close.dto.request.AccountStatus;
import com.kcc.banking.domain.account_close.dto.request.CloseTrade;
import com.kcc.banking.domain.account_close.dto.request.PaymentStatus;
import com.kcc.banking.domain.account_close.dto.response.CloseAccount;
import com.kcc.banking.domain.account_close.dto.response.InterestSum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountCloseMapper {

    int updateStatus(AccountStatus accountStatus);

    int addCancelTrade(CloseTrade closeTrade);

    InterestSum findInterestSum(String accountId);

    CloseAccount findCloseAccount(String accountId);

    int updatePaymentStatus(PaymentStatus paymentStatus);

}
