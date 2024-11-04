package com.kcc.banking.domain.reserve_transfer.mapper;

import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import com.kcc.banking.domain.reserve_transfer.dto.request.SearchReserve;
import com.kcc.banking.domain.trade.dto.request.TransferTradeCreate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveTransferMapper {

    void createReserveTransfer(ReserveTransferCreate reserveTransferCreate);

    void updateTransferStatus(@Param("reserveTransferId") String reserveTransferId, @Param("status") String status, @Param("failureReason") String failureReason);

    List<TransferTradeCreate> getPendingTransfers(SearchReserve searchReserve);
    
    // 당일 자동이체 -> 예약이체로 등록하기
    void insertScheduledAutoTransfer(ReserveTransferCreate reserveTransferCreate);

}

