package com.kcc.banking.domain.auto_transfer.mapper;


import com.kcc.banking.domain.auto_transfer.dto.request.AutoTransferCreate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AutoTransferMapper {

    int insertAutoTransfer(AutoTransferCreate autoTransferCreate);
}
