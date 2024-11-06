package com.kcc.banking.domain.business_day_management.service;

import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.dto.request.VaultCashRequest;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.interest.service.InterestServiceTest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BusinessDayManagementFacadeTest {

    private final BusinessDayService businessDayService;
    private final BusinessDayCloseService businessDayCloseService;
    private final InterestServiceTest interestService;


    /**
     * @param businessDateAndBranchId
     * @Discription - 복리 이자내역 테스트 코드
     */
    public void closeByManagerForTest(BusinessDateAndBranchId businessDateAndBranchId, CurrentData currentData) {
        // 지점 시재금 0원 저장
        VaultCashRequest build = VaultCashRequest.builder().vaultCash(BigDecimal.ZERO).build();
        // 지점 마감
        businessDayCloseService.closeBranchBusinessDay(businessDateAndBranchId, build);

        // 영업일 상태 변경
        businessDayService.businessDayStatusToClosed(businessDateAndBranchId.getBusinessDate());

        // 보통예금, 자율적금 단복리 이자 내역 추가 테스트
        String tradeNumber = businessDayCloseService.getClosingTradeNumber(businessDateAndBranchId);
        interestService.createInterestTest(tradeNumber, businessDateAndBranchId, currentData);
    }
}
