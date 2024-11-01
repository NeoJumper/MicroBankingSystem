package com.kcc.banking.domain.reserve_transfer.service;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import com.kcc.banking.domain.reserve_transfer.mapper.ReserveTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
