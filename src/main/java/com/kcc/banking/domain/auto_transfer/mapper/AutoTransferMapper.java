package com.kcc.banking.domain.auto_transfer.mapper;


import com.kcc.banking.domain.auto_transfer.dto.request.AutoTransferCreate;
import com.kcc.banking.domain.auto_transfer.dto.response.AutoTransferList;
import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveList;
import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AutoTransferMapper {

    int insertAutoTransfer(AutoTransferCreate autoTransferCreate);

    List<AutoTransferList> findScheduledAutoTransferList();

    List<AutoTransferList> findSelectedAutoTransferList();
    List<ReserveList> findReserveList();

}
