package com.kcc.banking.domain.bulk_transfer.mapper;

import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BulkTransferMapper {

    Long findNextBulkTransferId();

    int insertBulkTransfer(BulkTransferCreate bulkTransferCreate);
}
