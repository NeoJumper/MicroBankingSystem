package com.kcc.banking.domain.bulk_transfer.mapper;

import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferUpdate;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferSearch;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferProgressStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BulkTransferMapper {

    Long findNextBulkTransferId();

    BulkTransferProgressStatus findProgressStatus(Long bulkTransferId);

    int insertBulkTransfer(BulkTransferCreate bulkTransferCreate);

    List<BulkTransferDetail> findBulkTransferList(BulkTransferSearch bulkTransferSearch);

    BulkTransferDetail findBulkTransfer(Long bulkTransferId);

    int updateBulkTransfer(BulkTransferUpdate bulkTransferUpdate);
}
