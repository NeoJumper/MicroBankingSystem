package com.kcc.banking.domain.reserve_transfer.service;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import com.kcc.banking.domain.reserve_transfer.dto.request.SearchReserve;
import com.kcc.banking.domain.reserve_transfer.mapper.ReserveTransferMapper;
import com.kcc.banking.domain.trade.dto.request.TransferTradeCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReserveTransferService {

    private final ReserveTransferMapper reserveTransferMapper;
    private final CommonService commonService;

    /**
     * @Description
     * - 즉시이체, 대량이체 시 예약이체 등록 시 사용
     * - 금일 자동이체 등록 시 사용
     *
     */
    public void createReserveTransfer(ReserveTransferCreate reserveTransferCreate){
        CurrentData currentData = commonService.getCurrentData();

        reserveTransferCreate.setRegistrantId(currentData.getEmployeeId());
        reserveTransferCreate.setBranchId(currentData.getBranchId());

        reserveTransferMapper.createReserveTransfer(reserveTransferCreate);
    }
    public void createReserveTransferBySystem(ReserveTransferCreate reserveTransferCreate){
        reserveTransferMapper.createReserveTransfer(reserveTransferCreate);
    }

    public void updateReserveTransferStatus(String reserveTransferId, String status, String failureReason) {
        reserveTransferMapper.updateReserveTransferStatus(reserveTransferId, status, failureReason);
    }

    public List<TransferTradeCreate> getPendingTransfers(SearchReserve searchReserve){
        return reserveTransferMapper.getPendingTransfers(searchReserve);
    }


    public void updateMissedTransferOfAutoTransfer(String autoTransferId){
        reserveTransferMapper.updateMissedTransferOfAutoTransfer(autoTransferId);
    };

    public Timestamp findAutoReserveDate(String autoTransferId){
        return reserveTransferMapper.findAutoReserveDate(autoTransferId);
    }

    public String countAutoReserveSuccess(String autoTransferId){
        return reserveTransferMapper.countAutoReserveSuccess(autoTransferId);
    }
}

