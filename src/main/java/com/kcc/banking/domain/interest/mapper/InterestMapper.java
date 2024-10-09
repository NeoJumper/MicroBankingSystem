package com.kcc.banking.domain.interest.mapper;

import com.kcc.banking.domain.account_close.dto.request.PaymentStatus;
import com.kcc.banking.domain.account_close.dto.request.RollbackPaymentStatus;
import com.kcc.banking.domain.account_close.dto.response.InterestSum;
import com.kcc.banking.domain.interest.dto.request.InterestCreate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestMapper {

    void createInterest(InterestCreate interestCreate);

    InterestSum findInterestSum(String accountId);

    int updatePaymentStatus(PaymentStatus paymentStatus);

    int rollbackPaymentStatus(RollbackPaymentStatus rollbackPaymentStatus);
}
