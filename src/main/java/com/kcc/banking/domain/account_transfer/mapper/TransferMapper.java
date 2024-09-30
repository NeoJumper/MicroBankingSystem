package com.kcc.banking.domain.account_transfer.mapper;


import com.kcc.banking.domain.account_transfer.dto.request.TransferCreate;
import com.kcc.banking.domain.account_transfer.dto.response.TransferDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransferMapper {
    int insertTransfer(TransferDetail transferDetail);
    int updateAccountBalance(TransferDetail transferDetail);
}
