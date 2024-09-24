package com.kcc.banking.domain.account.mapper;

import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountOfModal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<AccountDetail> findAll();
    List<AccountOfModal> findAccount(SearchAccountOfModal searchAccountOfModal);
}
