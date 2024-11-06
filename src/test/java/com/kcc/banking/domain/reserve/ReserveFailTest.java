//package com.kcc.banking.domain.reserve;
//
//import com.kcc.banking.common.exception.CustomException;
//import com.kcc.banking.common.exception.ErrorCode;
//import com.kcc.banking.common.util.TransactionService;
//import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
//import com.kcc.banking.domain.common.service.CommonService;
//import com.kcc.banking.domain.reserve_transfer.service.ReserveTransferService;
//import com.kcc.banking.domain.trade.dto.request.TransferTradeCreate;
//import com.kcc.banking.domain.trade.service.AccountTradeFacade;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//public class ReserveFailTest {
//
//
//        @InjectMocks
//        private AccountTradeFacade accountTradeFacade;
//
//        @Mock
//        private ReserveTransferService reserveTransferService;
//
//        @Mock
//        private CommonService commonService;
//
//        @Mock
//        private TransactionService transactionService;
//
//        @BeforeEach
//        public void setUp() {
//            MockitoAnnotations.openMocks(this);
//        }
//
//        @Test
//        public void testProcessReserveTransferSuccess() {
//            // 준비
//            BusinessDay currentBusinessDay = new BusinessDay();
//            currentBusinessDay.setStatus("OPEN");
//
//            when(commonService.getCurrentBusinessDay()).thenReturn(currentBusinessDay);
//
//            TransferTradeCreate transferTradeCreate = new TransferTradeCreate();
//            transferTradeCreate.setReserveTransferId("1");
//            transferTradeCreate.setAccId("123");
//            transferTradeCreate.setTargetAccId("456");
//            transferTradeCreate.setTransferAmount(BigDecimal.valueOf(100));
//            transferTradeCreate.setMissedCount(0);
//
//            List<TransferTradeCreate> transferTradeCreateList = new ArrayList<>();
//            transferTradeCreateList.add(transferTradeCreate);
//
//            // 성공 처리
//            doNothing().when(reserveTransferService).updateTransferStatus(any(), eq("SUCCESS"), isNull());
//
//            // 실행
//            accountTradeFacade.processReserveTransfer(transferTradeCreateList);
//
//            // 검증
//            verify(reserveTransferService).updateTransferStatus("1", "SUCCESS", null);
//        }
//
//        @Test
//        public void testProcessReserveTransferFailureWithRetries() {
//            // 준비
//            BusinessDay currentBusinessDay = new BusinessDay();
//            currentBusinessDay.setStatus("OPEN");
//
//            when(commonService.getCurrentBusinessDay()).thenReturn(currentBusinessDay);
//
//            TransferTradeCreate transferTradeCreate = new TransferTradeCreate();
//            transferTradeCreate.setReserveTransferId("2");
//            transferTradeCreate.setAccId("123");
//            transferTradeCreate.setTargetAccId("456");
//            transferTradeCreate.setTransferAmount(BigDecimal.valueOf(100));
//            transferTradeCreate.setMissedCount(0);
//
//            List<TransferTradeCreate> transferTradeCreateList = new ArrayList<>();
//            transferTradeCreateList.add(transferTradeCreate);
//
//            // 이체 시도 시 예외 발생
//            doThrow(new CustomException(ErrorCode.NOT_FOUND_ACCOUNT)).when(transactionService).processTransfer(any(TransferTradeCreate.class));
//
//            // 실행
//            accountTradeFacade.processReserveTransfer(transferTradeCreateList);
//
//            // 검증
//            verify(reserveTransferService).updateTransferStatus("2", "FAIL", "TRANSFER_FAILED");
//            verify(reserveTransferService, times(1)).updateTransferStatus("2", "WAIT", null);
//        }
//
//        @Test
//        public void testProcessReserveTransferStopAfterMaxRetries() {
//            // 준비
//            BusinessDay currentBusinessDay = new BusinessDay();
//            currentBusinessDay.setStatus("OPEN");
//
//            when(commonService.getCurrentBusinessDay()).thenReturn(currentBusinessDay);
//
//            TransferTradeCreate transferTradeCreate = new TransferTradeCreate();
//            transferTradeCreate.setReserveTransferId("3");
//            transferTradeCreate.setAccId("123");
//            transferTradeCreate.setTargetAccId("456");
//            transferTradeCreate.setTransferAmount(BigDecimal.valueOf(1000));
//            transferTradeCreate.setMissedCount(3); // 최대 미납 횟수
//
//            List<TransferTradeCreate> transferTradeCreateList = new ArrayList<>();
//            transferTradeCreateList.add(transferTradeCreate);
//
//            // 실행
//            accountTradeFacade.processReserveTransfer(transferTradeCreateList);
//
//            // 검증
//            verify(reserveTransferService).updateTransferStatus("3", "STOP", "Max missed count exceeded");
//        }
//    }
