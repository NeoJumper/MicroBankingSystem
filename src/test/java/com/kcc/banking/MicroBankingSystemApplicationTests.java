package com.kcc.banking;

import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferUpdate;
import com.kcc.banking.domain.bulk_transfer.mapper.BulkTransferMapper;
import com.kcc.banking.domain.trade.mapper.TradeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Transactional
class MicroBankingSystemApplicationTests {

	@Autowired
	private TradeMapper tradeMapper;

	@Autowired
	private BulkTransferMapper bulkTransferMapper;

	@Rollback(false)
	@Test
	void contextLoads() {

		String dateString = "24/10/22"; // 주어진 날짜 문자열
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd"); // 날짜 형식 지정
		LocalDate localDate = LocalDate.parse(dateString, formatter); // 문자열을 LocalDate로 변환

// LocalDate를 LocalDateTime으로 변환 (기본 시간 00:00:00으로 설정)
		LocalDateTime localDateTime = localDate.atStartOfDay();

// LocalDateTime을 Timestamp로 변환
		Timestamp timestamp = Timestamp.valueOf(localDateTime);

		BulkTransferUpdate u = BulkTransferUpdate.builder()
				.id(1L)
				.accId("001-0000001-1234")
				.branchId(1L)
				.registrantId(1L)
				.successCnt(1)
				.failureCnt(0)
				.build();

		BulkTransferCreate b = BulkTransferCreate.builder()
				.id(1L)
				.accId("001-0000001-1234")
				.branchId(1L)
				.registrantId(1L)
				.tradeDate(timestamp)
				.amount(BigDecimal.valueOf(100))
				.successCnt(1)
				.failureCnt(0)
				.status("gooood")
				.build();

		bulkTransferMapper.insertBulkTransfer(b);

		bulkTransferMapper.updateBulkTransfer(u);
		
	}

}
