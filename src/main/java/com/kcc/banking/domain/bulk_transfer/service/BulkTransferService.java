package com.kcc.banking.domain.bulk_transfer.service;

import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferUpdate;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferSearch;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferProgressStatus;
import com.kcc.banking.domain.bulk_transfer.dto.response.TransferProgress;
import com.kcc.banking.domain.bulk_transfer.mapper.BulkTransferMapper;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class BulkTransferService {
    private final ConcurrentHashMap<Long, TransferProgress> progressMap = new ConcurrentHashMap<>();
    private final BulkTransferMapper bulkTransferMapper;

    public void updateProgress(Long transferId, TransferProgress progress) {
        progressMap.put(transferId, progress);
    }

    public TransferProgress getProgress(Long transferId) {
        return progressMap.get(transferId);
    }

    public void clearProgress(Long transferId) {
        progressMap.remove(transferId);
    }

    public Long getNextId() {
        return bulkTransferMapper.findNextBulkTransferId();
    }

    public void createBulkTransfer(BulkTransferCreate bulkTransferCreate) {
        bulkTransferMapper.insertBulkTransfer(bulkTransferCreate);
    }

    public List<BulkTransferDetail> getBulkTransferList(BulkTransferSearch bulkTransferSearch) {
        List<BulkTransferDetail> bulkTransferList = bulkTransferMapper.findBulkTransferList(bulkTransferSearch);
        bulkTransferList.forEach(bulkTransferDetail -> {
            bulkTransferDetail.setFailureAmount();
            String status = bulkTransferDetail.getStatus();
            if (status.equals("NOR"))
                bulkTransferDetail.setStatus("정상");
            else if(status.equals("FAIL"))
                bulkTransferDetail.setStatus("실패");
            else if(status.equals("WAIT"))
                bulkTransferDetail.setStatus("대기");
        });
        return bulkTransferList;

    }

    public BulkTransferDetail getBulkTransfer(Long bulkTransferId) {

        BulkTransferDetail bulkTransfer = bulkTransferMapper.findBulkTransfer(bulkTransferId);
        bulkTransfer.setFailureAmount();
        return bulkTransfer;

    }

    public int updateBulkTransfer(BulkTransferUpdate bulkTransferUpdate) {
        return bulkTransferMapper.updateBulkTransfer(bulkTransferUpdate);
    }

    public BulkTransferProgressStatus getBulkTransferProgressStatus(Long bulkTransferId) {
        return bulkTransferMapper.findProgressStatus(bulkTransferId);
    }

    public void createProgress(Long bulkTransferId, int totalCnt) {
        updateProgress(bulkTransferId, TransferProgress.builder()
                .successCount(0)
                .failureCount(0)
                .totalCount(totalCnt)
                .build());
    }

    public void increaseSuccessCnt(Long bulkTransferId, Long employeeId) {
        TransferProgress progress = getProgress(bulkTransferId);
        updateProgress(bulkTransferId, TransferProgress.builder()
                .successCount(progress.getSuccessCount() + 1)
                .failureCount(progress.getFailureCount())
                .totalCount(progress.getTotalCount())
                .build()
        );

        checkProgressStatus(bulkTransferId, employeeId);

    }


    public void increaseFailureCnt(Long bulkTransferId, Long employeeId) {
        TransferProgress progress = getProgress(bulkTransferId);
        updateProgress(bulkTransferId, TransferProgress.builder()
                .successCount(progress.getSuccessCount())
                .failureCount(progress.getFailureCount() + 1)
                .totalCount(progress.getTotalCount())
                .build()
        );

        checkProgressStatus(bulkTransferId, employeeId);
    }

    public void checkProgressStatus(Long bulkTransferId, Long employeeId) {
        TransferProgress progress = getProgress(bulkTransferId);
        System.out.println("총 개수 : " + progress.getTotalCount() + "  성공 개수 : " + progress.getSuccessCount() + "  실패 개수 : " + progress.getFailureCount());

        if(progress.getTotalCount() == progress.getSuccessCount() + progress.getFailureCount())
        {
            BulkTransferUpdate bulkTransferUpdate = BulkTransferUpdate.builder()
                    .id(bulkTransferId)
                    .status("NOR")
                    .modifierId(employeeId)
                    .build();
            System.out.println("작업 종료");
            bulkTransferMapper.updateBulkTransfer(bulkTransferUpdate);

            clearProgress(bulkTransferId);
        }
    }

}
