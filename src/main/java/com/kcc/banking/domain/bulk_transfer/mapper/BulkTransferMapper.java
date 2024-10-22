package com.kcc.banking.domain.bulk_transfer.mapper;

import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferUpdate;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BulkTransferMapper {

    Long findNextBulkTransferId();

    int insertBulkTransfer(BulkTransferCreate bulkTransferCreate);

    List<BulkTransferDetail> findBulkTransferList(BulkTransferSearch bulkTransferSearch);

    BulkTransferDetail findBulkTransfer(Long bulkTransferId);

    int updateAllBulkTransfer(BulkTransferUpdate bulkTransferUpdate);
}
