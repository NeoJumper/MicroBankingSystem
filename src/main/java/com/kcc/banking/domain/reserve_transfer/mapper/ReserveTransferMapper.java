package com.kcc.banking.domain.reserve_transfer.mapper;

import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReserveTransferMapper {

    void createReserveTransfer(ReserveTransferCreate reserveTransferCreate);
}
