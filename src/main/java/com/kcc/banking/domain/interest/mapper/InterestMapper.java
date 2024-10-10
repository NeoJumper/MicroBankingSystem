package com.kcc.banking.domain.interest.mapper;

import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.interest.dto.request.PaymentStatusRollback;
import com.kcc.banking.domain.interest.dto.request.PaymentStatusUpdate;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import com.kcc.banking.domain.interest.dto.request.InterestCreate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestMapper {

    void createInterest(InterestCreate interestCreate);

    // 세전 이자액 합계(해지 시 사용)
    InterestSum findInterestSum(String accountId);

    // 세전 이자액 합계(해지 취소 시 사용)
    InterestSum findPreTaxInterestSum(AccountIdWithExpireDate accountIdWithExpireDate);

    // 해지에 의한 이자 테이블 상태 및 지급일 변경
    int updateByClose(PaymentStatusUpdate paymentStatusUpdate);

    // 해지 취소에 의한 이자 테이블 상태 및 지급일 변경
    int updateByCloseCancel(PaymentStatusRollback paymentStatusRollback);
}
