package com.kcc.banking.domain.bulk_transfer.service;

import com.kcc.banking.domain.bulk_transfer.mapper.BulkTransferMapper;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BulkTransferService {
    private final BulkTransferMapper bulkTransferMapper;

    public Long getNextId() {
        return bulkTransferMapper.findNextBulkTransferId();
    }

    public void createBulkTransfer(BulkTransferCreate bulkTransferCreate) {
        bulkTransferMapper.insertBulkTransfer(bulkTransferCreate);
    }
}
