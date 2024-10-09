package com.kcc.banking.domain.account.mapper;

import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.response.*;
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

    // 계좌 개설 시 사용할 상품 정보(기본 이율 등) 가져오기
    AccountProductInfo findAccountProductInfo();

    // 계좌 개설완료 정보 호출
    AccountOpenResultOfModal findAccountOpenResultOfModal(String accId);

    List<AccountDetailForInterest> findAccountByBranchId(Long branchId);
}
