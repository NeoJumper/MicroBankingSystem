package com.kcc.banking.domain.reserve_transfer.service;

import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import com.kcc.banking.domain.reserve_transfer.mapper.ReserveTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReserveTransferService {

    private final ReserveTransferMapper reserveTransferMapper;

    public void createReserveTransfer(ReserveTransferCreate reserveTransferCreate){
        reserveTransferMapper.createReserveTransfer(reserveTransferCreate);
    }
}
