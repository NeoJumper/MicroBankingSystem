package com.kcc.banking.domain.bulk_transfer.controller;

import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferValidation;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferPreview;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferSearch;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferSearchResult;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferValidationResult;
import com.kcc.banking.domain.bulk_transfer.service.BulkTransferService;
import com.kcc.banking.domain.trade.dto.request.TransferTradeCreate;
import com.kcc.banking.domain.trade.dto.request.TransferTradeUpdate;
import com.kcc.banking.domain.trade.dto.response.PageDTO;
import com.kcc.banking.domain.trade.service.AccountTradeFacade;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BulkTransferRestController {

    private final BulkTransferService bulkTransferService;
    private final AccountTradeFacade accountTradeFacade;

    // 엑셀양식 다운로드
    @GetMapping("/api/employee/bulk-transfer/file-download")
    public void fileDownload(HttpServletResponse response) throws IOException {
        File f = new File("src/main/resources/bulk-transfer-upload.xlsx");
        // file 다운로드 설정
        response.setContentType("application/download");
        response.setContentLength((int)f.length());
        response.setHeader("Content-disposition", "attachment;filename=bulk-transfer-upload.xlsx");
        // response 객체를 통해서 서버로부터 파일 다운로드
        OutputStream os = response.getOutputStream();
        // 파일 입력 객체 생성
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }
    @PostMapping("/api/employee/bulk-transfer/excel-upload")
    public List<BulkTransferPreview> uploadCheck(@RequestParam("file") MultipartFile file) throws IOException {

        List<BulkTransferPreview> bulkTransferPreviewList = new ArrayList<>();


        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

        // 엑셀 파일의 0번째 시트
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);

            Cell cell = row.getCell(0); // 첫 번째 열

            // 첫 번째 열이 null이거나 빈 값이면 다음 행으로 건너뜁니다.
            if (cell == null || cell.getCellType() == CellType.BLANK) {
                continue;
            }

            DataFormatter formatter = new DataFormatter();

            BulkTransferPreview employee = BulkTransferPreview.builder()
                    .targetAccId(formatter.formatCellValue(row.getCell(0)))
                    .transferAmount(formatter.formatCellValue(row.getCell(1)))
                    .depositor(formatter.formatCellValue(row.getCell(2)))
                    .description(formatter.formatCellValue(row.getCell(3)))
                    .build();

            bulkTransferPreviewList.add(employee);


        }
        return bulkTransferPreviewList;
    }
    // 대량 이체 계좌 유효성 검증
    @PostMapping("/api/employee/bulk-transfer/validation")
    public ResponseEntity<BulkTransferValidationResult> validateBulkTransfer(@RequestBody List<BulkTransferValidation> bulkTransferValidationList) {
        BulkTransferValidationResult bulkTransferValidationResult = accountTradeFacade.validateBulkTransfer(bulkTransferValidationList);
        return ResponseEntity.ok(bulkTransferValidationResult);
    }

    // 대량 이체
    @PostMapping("/api/employee/bulk-transfer")
    public ResponseEntity<Long> processBulkTransfer(@RequestBody List<TransferTradeCreate> transferTradeCreateList) {
        Long bulkTransferId = accountTradeFacade.processBulkTransfer(transferTradeCreateList);
        return ResponseEntity.ok(bulkTransferId);
    }

    // 대량 이체 조회
    @GetMapping("/api/employee/bulk-transfer")
    public ResponseEntity<BulkTransferSearchResult> getBulkTransfer(@ModelAttribute BulkTransferSearch bulkTransferSearch) {
        List<BulkTransferDetail> bulkTransferList = bulkTransferService.getBulkTransferList(bulkTransferSearch);
        PageDTO pageDTO = new PageDTO(bulkTransferSearch.getCriteria(), bulkTransferList.size());
        return ResponseEntity.ok().body(BulkTransferSearchResult.of(bulkTransferList, pageDTO));
    }
}
