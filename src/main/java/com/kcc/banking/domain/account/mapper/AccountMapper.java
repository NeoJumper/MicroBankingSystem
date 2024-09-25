package com.kcc.banking.domain.account.mapper;

import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountProductInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<AccountDetail> findAll();
    // 계좌 개설할때 상품 정보 가져오기
    AccountProductInfo findAccountProductInfo();


    void openAccount(AccountCreate accountCreate);

    // 계좌번호 개설 시 계좌시퀀스 조회
    int getAccountSeq();

}
