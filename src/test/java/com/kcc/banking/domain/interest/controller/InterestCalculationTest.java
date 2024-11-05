package com.kcc.banking.domain.interest.controller;

import com.kcc.banking.domain.business_day_close.controller.BusinessDayCloseRestController;
import com.kcc.banking.domain.business_day_close.dto.request.VaultCashRequest;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import com.kcc.banking.domain.interest.mapper.InterestMapper;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@Transactional
public class InterestCalculationTest {

    private static final Logger logger = LoggerFactory.getLogger(InterestCalculationTest.class);

    @Autowired
    private BusinessDayCloseRestController businessDayCloseRestController;

    @Autowired
    private CommonService commonService;

    @Autowired
    private InterestMapper interestMapper;

    @BeforeEach
    public void setUp() {
        // 필요한 경우 테스트 데이터 설정
    }

    @Test
    @WithUserDetails(value = "1", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testInterestAccumulationOverYear() {
        // 시작 날짜와 종료 날짜 설정
        LocalDate startDate = LocalDate.of(2024, 8, 1);
        LocalDate endDate = startDate.plusMonths(12);

        // 계좌 ID 설정
        String accountIdSimple = "001-0000071-7171"; // 단리 계좌
        String accountIdCompound = "001-0000073-7373"; // 복리 계좌

        // 우대 이율 및 기본 이율 설정
        BigDecimal preferentialRateSimple = new BigDecimal("0.1");
        BigDecimal annualRateSimple = new BigDecimal("2.5");
        BigDecimal preferentialRateCompound = new BigDecimal("0.1");
        BigDecimal annualRateCompound = new BigDecimal("0.4");

        // 원금 설정
        BigDecimal principalSimple = new BigDecimal("100000");
        BigDecimal principalCompound = new BigDecimal("120000");

        BigDecimal accumulatedInterestSimple = BigDecimal.ZERO;
        BigDecimal accumulatedInterestCompound = BigDecimal.ZERO;

        // 총 예상 이자를 누적하기 위한 변수 초기화
        BigDecimal totalExpectedInterestSimple = BigDecimal.ZERO;
        BigDecimal totalExpectedInterestCompound = BigDecimal.ZERO;

        int monthIndex = 0;


        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            String businessDate = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            BusinessDateAndBranchId businessDateAndBranchId = new BusinessDateAndBranchId(businessDate, "1");

            // 영업일 마감 시뮬레이션
            businessDayCloseRestController.businessDayCloseOfManagerForTest(businessDateAndBranchId);

            // 매월 1일에 이자 계산 및 검증
            if (date.getDayOfMonth() == 1) {
                // 이자 내역 가져오기
                List<InterestDetails> simpleInterestDetails = interestMapper.findInterestDetails(accountIdSimple);
                List<InterestDetails> compoundInterestDetails = interestMapper.findInterestDetails(accountIdCompound);

                // 현재 월의 이자 내역 가져오기
                InterestDetails interestSimple = simpleInterestDetails.get(monthIndex);
                InterestDetails interestCompound = compoundInterestDetails.get(monthIndex);

                // 예상 이자 계산
                BigDecimal expectedInterestSimple = calculateExpectedSimpleInterest(principalSimple, annualRateSimple, preferentialRateSimple);
                BigDecimal expectedInterestCompound = calculateExpectedCompoundInterestForMonth(principalCompound, accumulatedInterestCompound, annualRateCompound, preferentialRateCompound);

                // 이자 금액 검증
//                assertEquals(expectedInterestSimple, interestSimple.getAmount().setScale(4, RoundingMode.DOWN));
//                assertEquals(expectedInterestCompound, interestCompound.getAmount().setScale(4, RoundingMode.DOWN));

                // 누적 이자 업데이트 (복리 계좌)
                accumulatedInterestCompound = accumulatedInterestCompound.add(interestCompound.getAmount());

                // 단리 계좌는 누적 이자 불필요하지만, 총 이자 계산을 위해 누적
                accumulatedInterestSimple = accumulatedInterestSimple.add(interestSimple.getAmount());

                // 총 예상 이자 누적
                totalExpectedInterestSimple = totalExpectedInterestSimple.add(expectedInterestSimple);
                totalExpectedInterestCompound = totalExpectedInterestCompound.add(expectedInterestCompound);

                monthIndex++;
            }
        }

        // 최종 검증
        //assertEquals(totalExpectedInterestSimple.setScale(4, RoundingMode.DOWN), accumulatedInterestSimple.setScale(4, RoundingMode.DOWN));
        //assertEquals(totalExpectedInterestCompound.setScale(4, RoundingMode.DOWN), accumulatedInterestCompound.setScale(4, RoundingMode.DOWN));

        // 단리 계좌 이자 내역 출력 및 합계 계산
        List<InterestDetails> simpleInterestDetails = interestMapper.findInterestDetails(accountIdSimple);
        BigDecimal totalSimpleInterest = BigDecimal.ZERO;

        logger.info("단리 계좌 이자 내역:");
        for (InterestDetails interestDetail : simpleInterestDetails) {
            logger.info("날짜: {}, 이자 금액: {}", interestDetail.getCreationDate(), interestDetail.getAmount());
            totalSimpleInterest = totalSimpleInterest.add(interestDetail.getAmount());
        }
        logger.info("단리 계좌 이자 총합: {}", totalSimpleInterest);
        logger.info("계산된 단리 계좌 이자 총합: {}", totalExpectedInterestSimple);

        // 복리 계좌 이자 내역 출력 및 합계 계산
        List<InterestDetails> compoundInterestDetails = interestMapper.findInterestDetails(accountIdCompound);
        BigDecimal totalCompoundInterest = BigDecimal.ZERO;

        logger.info("복리 계좌 이자 내역:");
        for (InterestDetails interestDetail : compoundInterestDetails) {
            logger.info("날짜: {}, 이자 금액: {}", interestDetail.getCreationDate(), interestDetail.getAmount());
            totalCompoundInterest = totalCompoundInterest.add(interestDetail.getAmount());
        }
        logger.info("복리 계좌 이자 총합: {}", totalCompoundInterest);

        logger.info("계산된 복리 계좌 이자 총합: {}", totalExpectedInterestCompound);
    }

    private BigDecimal calculateExpectedSimpleInterest(BigDecimal principal, BigDecimal annualRate, BigDecimal preferentialRate) {
        BigDecimal totalRate = annualRate.add(preferentialRate).divide(BigDecimal.valueOf(100), 8, RoundingMode.DOWN);
        BigDecimal monthlyRate = totalRate.divide(BigDecimal.valueOf(12), 8, RoundingMode.DOWN);
        return principal.multiply(monthlyRate).setScale(4, RoundingMode.DOWN);
    }

    private BigDecimal calculateExpectedCompoundInterestForMonth(BigDecimal principal, BigDecimal accumulatedInterest, BigDecimal annualRate, BigDecimal preferentialRate) {
        BigDecimal totalRate = annualRate.add(preferentialRate).divide(BigDecimal.valueOf(100), 8, RoundingMode.DOWN);
        BigDecimal monthlyRate = totalRate.divide(BigDecimal.valueOf(12), 8, RoundingMode.DOWN);
        BigDecimal balanceWithInterest = principal.add(accumulatedInterest);
        BigDecimal interest = balanceWithInterest.multiply(monthlyRate).setScale(4, RoundingMode.DOWN);
        return interest;
    }

}