package com.kcc.banking.domain.account.mapper;

import com.kcc.banking.domain.account.dto.response.AccountDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<AccountDetail> findAll();
}
