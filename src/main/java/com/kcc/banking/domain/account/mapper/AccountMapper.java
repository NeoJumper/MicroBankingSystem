package com.kcc.banking.domain.account.mapper;

import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    String findPasswordByAccNumber(String accountNumber);
    // 전체 계좌 목록 조회
    List<AccountDetail> findAll();
    // 모달 조회 후 목록 요청
    List<AccountOfModal> findAccount(SearchAccountOfModal searchAccountOfModal);
    // 계좌 상세정보 조회
    AccountDetail getAccountDetail(String accountId);

    // 계좌 개설할때 상품 정보 가져오기
    AccountProductInfo findAccountProductInfo();

    void openAccount(AccountCreate accountCreate);

    // 계좌번호 개설 시 계좌시퀀스 조회
    int getAccountSeq();

    // 계좌 개설완료 정보 호출
    AccountOpenResultOfModal getAccountOpenResultOfModal(String accId);

    List<AccountDetailForInterest> findAccountByBranchId(Long branchId);
}
