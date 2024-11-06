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
				.tradeDate("24/10/22")
				.amount(BigDecimal.valueOf(100))
				.successCnt(1)
				.failureCnt(0)
				.status("gooood")
				.build();

		bulkTransferMapper.insertBulkTransfer(b);

		bulkTransferMapper.updateBulkTransfer(u);
		
	}

}
