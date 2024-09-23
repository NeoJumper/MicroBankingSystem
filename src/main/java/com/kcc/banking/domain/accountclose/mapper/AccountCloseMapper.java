package com.kcc.banking.domain.accountclose.mapper;

import com.kcc.banking.domain.accountclose.dto.request.AccountStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountCloseMapper {

    int updateStatus(AccountStatus accountStatus);

}
