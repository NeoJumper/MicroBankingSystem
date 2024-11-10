package com.kcc.banking.domain.account.mapper;

import com.kcc.banking.domain.account.dto.request.AccountUpdate;
import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.request.SearchProductOfModal;
import com.kcc.banking.domain.account.dto.response.*;
import com.kcc.banking.domain.trade.dto.request.CloseAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    String findPasswordByAccNumber(String accountNumber);

    // 모달 내 검색조건에 의한 계좌목록 조회
    List<AccountOfModal> findAccountsBySearchOption(SearchAccountOfModal searchAccountOfModal);

    // 계좌 상세정보 조회
    AccountDetail getAccountDetail(String accountId);

    // 계좌 개설
    void openAccount(AccountCreate accountCreate);

    // 계좌번호 개설 시 계좌시퀀스 조회
    int getAccountSeq();

    // 계좌 개설완료 정보 호출
    AccountOpenResultOfModal findAccountOpenResultOfModal(String accId);

    List<AccountDetailForInterest> findAccountByBranchId(Long branchId);

    // 해지 계좌 조회
    CloseAccount findCloseAccount(String accountId);

    // 계좌 해지일 조회
    String findExpireDateById(String id);

    // 계좌 잔액 업데이트
    int partialUpdateAccount(AccountUpdate accountUpdate);


    // 한 고객의 입출금 계좌 전체 조회
    List<AccountOfModal> findAllAccountOfOneCustomer(String customerId);

    // 예적금 상품 찾기 모달 조건별 조회
    List<ProductOfModal> findAccountProductList(SearchProductOfModal searchProductOfModal);

    // 적금 해지 검색 모달 조회
    CloseSavingsAccountTotal findCloseSavingsAccountDetail(String accountId);


    // 자유적금 해지 시 조회
    CloseSavingsFlexibleAccountTotal findCloseSavingsFlexibleAccountById(String accountId);

    // 정기적금 정기 입금 금액 + 거래횟수 조회
    CloseFixedAccountDetail findFixedDetailOfSavingsAccount(String accId);

}
