package com.kcc.banking.domain.bulk_transfer.service;

import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferUpdate;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferSearch;
import com.kcc.banking.domain.bulk_transfer.mapper.BulkTransferMapper;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<BulkTransferDetail> getBulkTransferList(BulkTransferSearch bulkTransferSearch) {
        return bulkTransferMapper.findBulkTransferList(bulkTransferSearch);

    }

    public BulkTransferDetail getBulkTransfer(Long bulkTransferId) {
        return bulkTransferMapper.findBulkTransfer(bulkTransferId);
    }

    public int updateAllBulkTransfer(BulkTransferUpdate bulkTransferUpdate) {
        return bulkTransferMapper.updateBulkTransfer(bulkTransferUpdate);
    }
}
