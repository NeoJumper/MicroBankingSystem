package com.kcc.banking.domain.bulk_transfer.dto.response;

import com.kcc.banking.domain.trade.dto.response.PageDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class BulkTransferSearchResult {

    private List<BulkTransferDetail> bulkTransferDetailList;
    private PageDTO pageDTO;

    @Builder
    public BulkTransferSearchResult(List<BulkTransferDetail> bulkTransferDetailList, PageDTO pageDTO) {
        this.bulkTransferDetailList = bulkTransferDetailList;
        this.pageDTO = pageDTO;
    }

    public static BulkTransferSearchResult of(List<BulkTransferDetail> bulkTransferList, PageDTO pageDTO) {
        return BulkTransferSearchResult.builder()
                .bulkTransferDetailList(bulkTransferList)
                .pageDTO(pageDTO).build();
    }
}
