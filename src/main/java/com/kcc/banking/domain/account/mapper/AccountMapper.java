package com.kcc.banking.domain.account.mapper;

import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountOfModal;
import com.kcc.banking.domain.account.dto.response.AccountOpenResultOfModal;
import com.kcc.banking.domain.account.dto.response.AccountProductInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    String findPasswordByAccNumber(String accountNumber);
    List<AccountDetail> findAll();
    List<AccountOfModal> findAccount(SearchAccountOfModal searchAccountOfModal);
    // 계좌 개설할때 상품 정보 가져오기
    AccountProductInfo findAccountProductInfo();


    void openAccount(AccountCreate accountCreate);

    // 계좌번호 개설 시 계좌시퀀스 조회
    int getAccountSeq();

    // 계좌 개설완료 정보 호출
    AccountOpenResultOfModal getAccountOpenResultOfModal(String accId);
}
