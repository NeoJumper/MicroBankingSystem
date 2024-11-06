package com.kcc.banking.domain.interest.controller;

import com.kcc.banking.domain.account.dto.response.CloseSavingsFlexibleAccountTotal;
import com.kcc.banking.domain.account.mapper.AccountMapper;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ActiveProfiles("test") // "test" 프로파일을 활성화
@SpringBootTest
@Transactional
public class InterestCalculationTest {

    private static final Logger logger = LoggerFactory.getLogger(InterestCalculationTest.class);

    @Autowired
    private BusinessDayCloseRestController businessDayCloseRestController;

    @Autowired
    private AccountMapper accountMapper;

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
        LocalDate startDate = LocalDate.of(2024, 8, 2);
        LocalDate endDate = startDate.plusMonths(12);

        // 단리 계좌
        CloseSavingsFlexibleAccountTotal accountSimple = accountMapper.findCloseSavingsFlexibleAccountById("001-0000071-7171");
        // 복리 계좌
        CloseSavingsFlexibleAccountTotal accountCompound = accountMapper.findCloseSavingsFlexibleAccountById("001-0000073-7373");

        // 누적합 계산용 변수 초기화
        BigDecimal accumulatedInterestSimple = BigDecimal.ZERO;
        BigDecimal accumulatedInterestCompound = BigDecimal.ZERO;

        // 총 예상 이자를 누적하기 위한 변수 초기화
        BigDecimal totalExpectedInterestSimple = BigDecimal.ZERO;
        BigDecimal totalExpectedInterestCompound = BigDecimal.ZERO;

        int monthIndex = 0;

        List<InterestDetails> expectedSimpleInterests = new ArrayList<>();
        List<InterestDetails> expectedCompoundInterests = new ArrayList<>();

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            // for문 내부에서 date는 1일씩 증가
            LocalDateTime startDateTime = date.atStartOfDay();
            String businessDate = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            BusinessDateAndBranchId businessDateAndBranchId = new BusinessDateAndBranchId(businessDate, "1");
            // 영업일 마감 시뮬레이션 - 내부 로직에서 interest 테이블에 마감 시 이자내역 생성
            businessDayCloseRestController.businessDayCloseOfManagerForTest(businessDateAndBranchId);
            // 매월 1일에 이자 계산 및 검증
            if (date.getDayOfMonth() == 1) {
                // 이자 내역 가져오기
                List<InterestDetails> simpleInterestDetails = interestMapper.findInterestDetails(accountSimple.getId());
                List<InterestDetails> compoundInterestDetails = interestMapper.findInterestDetails(accountCompound.getId());

                // 현재 월의 이자 내역 가져오기
                InterestDetails interestSimple = simpleInterestDetails.get(monthIndex);
                InterestDetails interestCompound = compoundInterestDetails.get(monthIndex);

                // 예상 이자 계산
                BigDecimal expectedInterestSimple = calculateExpectedSimpleInterest(accountSimple.getBalance(), accountSimple.getInterestRate(), accountSimple.getPreferentialInterestRate());
                BigDecimal expectedInterestCompound = calculateExpectedCompoundInterestForMonth(accountCompound.getBalance(), accumulatedInterestCompound, accountCompound.getInterestRate(), accountCompound.getPreferentialInterestRate());

                // 이자 금액 검증
                assertEquals(expectedInterestSimple, interestSimple.getAmount().setScale(4, RoundingMode.DOWN));
                assertEquals(expectedInterestCompound, interestCompound.getAmount().setScale(4, RoundingMode.DOWN));

                // 누적 이자 업데이트 (복리 계좌)
                accumulatedInterestCompound = accumulatedInterestCompound.add(interestCompound.getAmount());

                // 단리 계좌는 누적 이자 불필요하지만, 총 이자 계산을 위해 누적
                accumulatedInterestSimple = accumulatedInterestSimple.add(interestSimple.getAmount());

                // 총 예상 이자 누적
                totalExpectedInterestSimple = totalExpectedInterestSimple.add(expectedInterestSimple);
                totalExpectedInterestCompound = totalExpectedInterestCompound.add(expectedInterestCompound);

                // 계산된 이자 내역 저장
                expectedSimpleInterests.add(InterestDetails.builder()
                        .creationDate(businessDate)
                        .balance(accountSimple.getBalance())
                        .amount(expectedInterestSimple)
                        .interestRate(accountSimple.getInterestRate())
                        .preferentialInterestRate(accountSimple.getPreferentialInterestRate())
                        .build());

                expectedCompoundInterests.add(InterestDetails.builder()
                        .creationDate(businessDate)
                        .balance(accountCompound.getBalance())
                        .amount(expectedInterestCompound)
                        .interestRate(accountCompound.getInterestRate())
                        .preferentialInterestRate(accountCompound.getPreferentialInterestRate())
                        .build());

                monthIndex++;
            }
        }

        // 최종 검증
        //assertEquals(totalExpectedInterestSimple.setScale(4, RoundingMode.DOWN), accumulatedInterestSimple.setScale(4, RoundingMode.DOWN));
        //assertEquals(totalExpectedInterestCompound.setScale(4, RoundingMode.DOWN), accumulatedInterestCompound.setScale(4, RoundingMode.DOWN));

        // 단리 계좌 이자 내역 출력 및 합계 계산
        List<InterestDetails> simpleInterestDetails = interestMapper.findInterestDetails(accountSimple.getId());
        BigDecimal totalSimpleInterest = BigDecimal.ZERO;

        logger.info("단리 계좌 이자 내역:");
        for (InterestDetails interestDetail : simpleInterestDetails) {
            logger.info("날짜: {}, 이자 금액: {}, 잔액: {}", interestDetail.getCreationDate(), interestDetail.getAmount(), interestDetail.getBalance());
            totalSimpleInterest = totalSimpleInterest.add(interestDetail.getAmount());
        }
        logger.info("단리 계좌 이자 총합: {}", totalSimpleInterest);

        logger.info("계산된 단리 계좌 이자 내역:");
        for(InterestDetails interestDetail : expectedSimpleInterests) {
            logger.info("날짜: {}, 이자 금액: {}, 잔액: {}", interestDetail.getCreationDate(), interestDetail.getAmount(), interestDetail.getBalance());
        }
        logger.info("계산된 단리 계좌 이자 총합: {}", totalExpectedInterestSimple);

        // 복리 계좌 이자 내역 출력 및 합계 계산
        List<InterestDetails> compoundInterestDetails = interestMapper.findInterestDetails(accountCompound.getId());
        BigDecimal totalCompoundInterest = BigDecimal.ZERO;

        logger.info("복리 계좌 이자 내역:");
        for (InterestDetails interestDetail : compoundInterestDetails) {
            logger.info("날짜: {}, 이자 금액: {}, 잔액: {}", interestDetail.getCreationDate(), interestDetail.getAmount(), interestDetail.getBalance());
            totalCompoundInterest = totalCompoundInterest.add(interestDetail.getAmount());
        }
        logger.info("복리 계좌 이자 총합: {}", totalCompoundInterest);

        logger.info("계산된 복리 이자 내역:");
        for(InterestDetails interestDetail : expectedCompoundInterests) {
            logger.info("날짜: {}, 이자 금액: {}, 잔액: {}", interestDetail.getCreationDate(), interestDetail.getAmount(), interestDetail.getBalance());
        }
        logger.info("계산된 복리 계좌 이자 총합: {}", totalExpectedInterestCompound);
    }

    private BigDecimal calculateExpectedSimpleInterest(BigDecimal principal, BigDecimal annualRate, BigDecimal preferentialRate) {
        BigDecimal totalRate = annualRate.add(preferentialRate).divide(BigDecimal.valueOf(100));
        BigDecimal monthlyRate = totalRate.divide(BigDecimal.valueOf(12), 4, RoundingMode.DOWN);
        return principal.multiply(monthlyRate).setScale(4, RoundingMode.DOWN);
    }

    private BigDecimal calculateExpectedCompoundInterestForMonth(BigDecimal principal, BigDecimal accumulatedInterest, BigDecimal annualRate, BigDecimal preferentialRate) {
        BigDecimal totalRate = annualRate.add(preferentialRate).divide(BigDecimal.valueOf(100));
        BigDecimal monthlyRate = totalRate.divide(BigDecimal.valueOf(12), 4, RoundingMode.DOWN);
        BigDecimal balanceWithInterest = principal.add(accumulatedInterest);
        BigDecimal interest = balanceWithInterest.multiply(monthlyRate).setScale(4, RoundingMode.DOWN);
        return interest;
    }

}