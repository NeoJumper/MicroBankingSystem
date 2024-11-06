package com.kcc.banking.domain.interest.mapper;

import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.interest.dto.request.*;
import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestMapper {

    // 일별 이자 생성
    void createInterest(InterestCreate interestCreate);
    // 월별 이자 생성
    void createInterestSaving(SavingInterestCreate savingInterestCreate);

    // 세전 이자액 합계(해지 시 사용)
    InterestSum findInterestSum(String accountId);

    // 세전 이자액 합계(해지 취소 시 사용)
    InterestSum findPreTaxInterestSum(AccountIdWithExpireDate accountIdWithExpireDate);

    // 해지에 의한 이자 테이블 상태 및 지급일 변경
    int updateByClose(PaymentStatusUpdate paymentStatusUpdate);

    // 해지 취소에 의한 이자 테이블 상태 및 지급일 변경
    int updateByCloseCancel(PaymentStatusRollback paymentStatusRollback);

    // 영업일 되돌리기에 의한 이자 삭제
    void deleteInterest(BusinessDateAndBranchId businessDateAndBranchId);

    // 계좌번호로 이자내역 목록 불러오기
    List<InterestDetails> findInterestDetails(String accountId);

    void updateByCloseWithInterestList(PaymentUpdate paymentUpdate);
}
