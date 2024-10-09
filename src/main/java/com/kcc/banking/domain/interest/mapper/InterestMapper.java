package com.kcc.banking.domain.interest.mapper;

import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.interest.dto.request.PaymentStatus;
import com.kcc.banking.domain.interest.dto.request.RollbackPaymentStatus;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import com.kcc.banking.domain.interest.dto.request.InterestCreate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestMapper {

    void createInterest(InterestCreate interestCreate);

    // 이자 합계
    InterestSum findInterestSum(String accountId);

    // 롤백해야 할 이자 합계
    InterestSum findRollbackInterestSum(AccountIdWithExpireDate accountIdWithExpireDate);

    int updatePaymentStatus(PaymentStatus paymentStatus);

    int rollbackPaymentStatus(RollbackPaymentStatus rollbackPaymentStatus);
}
