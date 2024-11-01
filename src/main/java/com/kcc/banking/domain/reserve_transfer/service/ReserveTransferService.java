package com.kcc.banking.domain.reserve_transfer.service;

import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import com.kcc.banking.domain.reserve_transfer.mapper.ReserveTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReserveTransferService {

    private final ReserveTransferMapper reserveTransferMapper;

    /**
     * @Description
     * - 즉시이체, 대량이체 시 예약이체 등록 시 사용
     * - 금일 자동이체 등록 시 사용
     *
     */
    public void createReserveTransfer(ReserveTransferCreate reserveTransferCreate){
        reserveTransferMapper.createReserveTransfer(reserveTransferCreate);
    }
}
