package com.kcc.banking.domain.interest.controller;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.account.dto.request.FlexibleSavingsAccountClose;
import com.kcc.banking.domain.account.dto.response.AccountCloseResult;
import com.kcc.banking.domain.account.dto.response.CloseSavingsFlexibleAccountTotal;
import com.kcc.banking.domain.account.service.AccountCloseFacadeTest;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.controller.BusinessDayCloseControllerTest;
import com.kcc.banking.domain.business_day_close.dto.request.BranchClosingCreate;
import com.kcc.banking.domain.business_day_close.dto.request.EmployeeClosingCreate;
import com.kcc.banking.domain.business_day_close.mapper.BusinessDayCloseMapper;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import com.kcc.banking.domain.interest.mapper.InterestMapper;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ActiveProfiles("test") // "test" 프로파일을 활성화
@SpringBootTest
public class InterestCalculationTest {

    private static final Logger logger = LoggerFactory.getLogger(InterestCalculationTest.class);


    @Autowired
    private InterestMapper interestMapper;

    @Autowired
    private AccountCloseFacadeTest accountCloseFacadeTest;

    @Autowired
    private BusinessDayCloseControllerTest businessDayCloseControllerTest;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BusinessDayService businessDayService;

    @Autowired
    private BusinessDayCloseMapper businessDayCloseMapper;

    @BeforeEach
    public void setUp() {
        // 필요한 경우 테스트 데이터 설정
    }

    @Test
    @DisplayName("데이터 생성")
    void test() throws Exception {
        String ddl = new String(Files.readAllBytes(Paths.get("src/test/resources/test-schema.sql")));
        // 구문을 세미콜론(;)으로 분리하여 개별적으로 실행
        String[] ddlStatements = ddl.split(";");

        for (String statement : ddlStatements) {
            // 빈 구문이 아닌 경우에만 실행
            if (!statement.trim().isEmpty()) {
                jdbcTemplate.execute(statement.trim());
            }
        }

        System.out.println("DDL 구문 실행 완료");


        // 3개월 이후 영업일
        String firstPlsql = """
            DECLARE
                v_date DATE := TO_DATE('2024-08-01', 'YYYY-MM-DD');
                v_end_date DATE := TRUNC(ADD_MONTHS(v_date, 10));
            BEGIN
                WHILE v_date <= v_end_date LOOP
                    INSERT INTO Business_day (business_date, status, is_current_business_day, version)
                    VALUES (
                        v_date,
                        CASE
                            WHEN TO_CHAR(v_date, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') IN ('SAT', 'SUN') THEN 'HOLIDAY'
                            ELSE 'SCHEDULED'
                        END,
                        'FALSE',
                        1
                    );
                    v_date := v_date + 1;
                END LOOP;
            END;
            """;

        // 3개월 이전 영업일
        String secondPlsql = """
            DECLARE
                v_end_date DATE := TO_DATE('2024-08-01', 'YYYY-MM-DD');
                v_start_date DATE := TRUNC(ADD_MONTHS(v_end_date, -3));
            BEGIN
                WHILE v_start_date < v_end_date LOOP
                    INSERT INTO Business_day (business_date, status, is_current_business_day, version)
                    VALUES (
                        TRUNC(v_start_date),
                        CASE
                            WHEN TO_CHAR(v_start_date, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') IN ('SAT', 'SUN') THEN 'HOLIDAY'
                            ELSE 'CLOSED'
                        END,
                        'FALSE',
                        1  
                    );
                    v_start_date := v_start_date + 1;
                END LOOP;
            END;
            """;

        // UPDATE 구문
        String updateSql = """
            UPDATE business_day 
            SET
                status = 'OPEN',
                is_current_business_day = 'TRUE',
                version = 2
            WHERE business_date = '24/08/01'
            """;

        jdbcTemplate.execute(firstPlsql);
        jdbcTemplate.execute(secondPlsql);
        jdbcTemplate.execute(updateSql);

        System.out.println("영업일 초기 데이터 삽입");

        String dml = new String(Files.readAllBytes(Paths.get("src/test/resources/test-data.sql")));
        // 구문을 세미콜론(;)으로 분리하여 개별적으로 실행
        String[] dmlStatements = dml.split(";");

        for (String statement : dmlStatements) {
            // 빈 구문이 아닌 경우에만 실행
            if (!statement.trim().isEmpty()) {
                jdbcTemplate.execute(statement.trim());
            }
        }

        System.out.println("DML 구문 실행 완료");

    }

    @Test
    @WithUserDetails(value = "1", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testInterestAccumulationOverYear() {
        // 시작 날짜와 종료 날짜 설정
        LocalDate startDate = LocalDate.of(2024, 8, 1);
        LocalDate endDate = startDate.plusMonths(5);


        String startDay = startDate.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 단리 계좌
        CloseSavingsFlexibleAccountTotal accountSimple = accountCloseFacadeTest.getFlexibleSavingsAccountTest("001-0000071-7171", startDay);
        // 복리 계좌
        CloseSavingsFlexibleAccountTotal accountCompound = accountCloseFacadeTest.getFlexibleSavingsAccountTest("001-0000073-7373", startDay);

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
            CurrentData currentData = CurrentData.builder()
                    .branchId(1L)
                    .branchName("TEST BRANCH")
                    .employeeId(1L)
                    .employeeName("김철수")
                    .currentBusinessDate(businessDate).build();

            // 영업일 마감 시뮬레이션 - 내부 로직에서 interest 테이블에 마감 시 이자내역 생성
            businessDayCloseControllerTest.businessDayCloseOfManagerForTest(businessDateAndBranchId, currentData);

            // 영업일 변경
            BusinessDay currentBusinessDay = businessDayService.getCurrentBusinessDay();
            BusinessDay nextBusinessDay = businessDayService.getNextBusinessDay();

            BusinessDayChange businessDayChange = new BusinessDayChange();
            businessDayChange.setBusinessDateToChange(businessDate);
            businessDayChange.setPrevCashBalanceOfBranch(BigDecimal.valueOf(10000000));
            List<WorkerData> list = new ArrayList<>();
            list.add(WorkerData.builder().id("1").prevCashBalance(BigDecimal.valueOf(100000)).status("CLOSED").build());
            businessDayChange.setWorkerDataList(list);

            // CLOSED TRUE - FALSE
            businessDayService.businessDayChange(currentBusinessDay, nextBusinessDay);
            Long tradeNumber = businessDayCloseMapper.getNextTradeNumberVal();

            businessDateAndBranchId.setBusinessDate(nextBusinessDay.getBusinessDate());
            // 영업일 생성
            createEmployeeClosing(businessDayChange.getWorkerDataList(),businessDayChange.getBusinessDateToChange(), businessDateAndBranchId, tradeNumber);
            createBranchClosing(businessDayChange.getBusinessDateToChange(), businessDayChange.getPrevCashBalanceOfBranch(), tradeNumber, businessDateAndBranchId);


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
                //assertEquals(expectedInterestSimple, interestSimple.getAmount().setScale(4, RoundingMode.DOWN));
                //assertEquals(expectedInterestCompound, interestCompound.getAmount().setScale(4, RoundingMode.DOWN));

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

        // 이자내역 log
        interestLog(accountSimple, expectedSimpleInterests, totalExpectedInterestSimple, accountCompound, expectedCompoundInterests, totalExpectedInterestCompound);

        // 해지 테스트
        closeTest(endDate);

    }

    private void createBranchClosing(String businessDateToChange, BigDecimal prevCashBalanceOfBranch, Long tradeNumber, BusinessDateAndBranchId businessDateAndBranchId) {
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        BranchClosingCreate branchClosingCreate = BranchClosingCreate.builder()
                .closingDate(businessDateToChange)
                .branchId(businessDateAndBranchId.getBranchId())
                .status("OPEN")
                .prevCashBalance(prevCashBalanceOfBranch)
                .tradeNumber(tradeNumber)
                .registrantId(String.valueOf(loginMemberId))
                .build();

        businessDayCloseMapper.insertBranchClosing(branchClosingCreate);
    }

    public void createEmployeeClosing(List<WorkerData> workerDataList, String businessDateToChange, BusinessDateAndBranchId businessDateAndBranchId, Long tradeNumber) {

        List<EmployeeClosingCreate> employeeClosingCreateList = workerDataList.stream().map(workerData -> EmployeeClosingCreate.of(workerData,businessDateToChange, businessDateAndBranchId, tradeNumber))
                .toList();

        businessDayCloseMapper.batchInsertEmployeeClosing(employeeClosingCreateList);

    }

    private void interestLog(CloseSavingsFlexibleAccountTotal accountSimple, List<InterestDetails> expectedSimpleInterests, BigDecimal totalExpectedInterestSimple, CloseSavingsFlexibleAccountTotal accountCompound, List<InterestDetails> expectedCompoundInterests, BigDecimal totalExpectedInterestCompound) {
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
            logger.info("날짜: {}, 이자 금액: {}, 이자율: {}, 잔액: {}", interestDetail.getCreationDate(), interestDetail.getAmount(), interestDetail.getBalance());
        }
        logger.info("계산된 단리 계좌 이자 총합: {}", totalExpectedInterestSimple);

        // 복리 계좌 이자 내역 출력 및 합계 계산
        List<InterestDetails> compoundInterestDetails = interestMapper.findInterestDetails(accountCompound.getId());
        BigDecimal totalCompoundInterest = BigDecimal.ZERO;

        logger.info("복리 계좌 이자 내역:");
        for (InterestDetails interestDetail : compoundInterestDetails) {
            logger.info("날짜: {}, 이자 금액: {}, 이자율: {}, 잔액: {}", interestDetail.getCreationDate(), interestDetail.getAmount(), interestDetail.getInterestRate() ,interestDetail.getBalance());
            totalCompoundInterest = totalCompoundInterest.add(interestDetail.getAmount());
        }
        logger.info("복리 계좌 이자 총합: {}", totalCompoundInterest);

        logger.info("계산된 복리 이자 내역:");
        for(InterestDetails interestDetail : expectedCompoundInterests) {
            logger.info("날짜: {}, 이자 금액: {}, 잔액: {}", interestDetail.getCreationDate(), interestDetail.getAmount(), interestDetail.getBalance());
        }
        logger.info("계산된 복리 계좌 이자 총합: {}", totalExpectedInterestCompound);
    }

    private void closeTest(LocalDate endDate) {
        String closeDay = endDate.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 단리 계좌
        CloseSavingsFlexibleAccountTotal accountSimple = accountCloseFacadeTest.getFlexibleSavingsAccountTest("001-0000071-7171", closeDay);

        FlexibleSavingsAccountClose simpleClose = FlexibleSavingsAccountClose.builder()
                .accId(accountSimple.getId())
                .amount(accountSimple.getTotalAmount())
                .status("CLS")
                .description("단리 자유 적금 해지")
                .tradeType("CLOSE")
                .closeType(accountSimple.getCloseType().getDescription())
                .interestDetailsList(accountSimple.getInterestDetailsList())
                .build();

        // 복리 계좌
        CloseSavingsFlexibleAccountTotal accountCompound = accountCloseFacadeTest.getFlexibleSavingsAccountTest("001-0000073-7373", closeDay);

        FlexibleSavingsAccountClose compoundClose = FlexibleSavingsAccountClose.builder()
                .accId(accountCompound.getId())
                .amount(accountCompound.getTotalAmount())
                .status("CLS")
                .description("복리 자유 적금 해지")
                .tradeType("CLOSE")
                .closeType(accountCompound.getCloseType().getDescription())
                .interestDetailsList(accountCompound.getInterestDetailsList())
                .build();

        String businessDate = endDate.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BusinessDay businessDay = new BusinessDay();
        businessDay.setBusinessDate(businessDate);
        businessDay.setStatus("OPEN");
        businessDay.setIsCurrentBusinessDay("TRUE");

        CurrentData currentData = CurrentData.builder()
                .branchId(1L)
                .branchName("TEST BRANCH")
                .employeeId(1L)
                .employeeName("김철수")
                .currentBusinessDate(businessDate).build();

        AccountCloseResult simpleAccountCloseResult = accountCloseFacadeTest.closeFlexibleSavingsAccountForTest(simpleClose, currentData, businessDay);
        closeLog(simpleAccountCloseResult);

        AccountCloseResult compoundAccountCloseResult = accountCloseFacadeTest.closeFlexibleSavingsAccountForTest(compoundClose, currentData, businessDay);
        closeLog(compoundAccountCloseResult);


    }

    private static void closeLog(AccountCloseResult accountCloseResult) {
        logger.info("자유 적금 해지 내역");
        logger.info("자유 적금 해지 내역: accountId={}, accountStatus={}, openDate={}, accountPreInterRate={}, accountBal={}, customerName={}, customerId={}, productName={}, productInterRate={}, productTaxRate={}, amountSum={}",
                accountCloseResult.getAccountId(),
                accountCloseResult.getAccountStatus(),
                accountCloseResult.getOpenDate(),
                accountCloseResult.getAccountPreInterRate(),
                accountCloseResult.getAccountBal(),
                accountCloseResult.getCustomerName(),
                accountCloseResult.getCustomerId(),
                accountCloseResult.getProductName(),
                accountCloseResult.getProductInterRate(),
                accountCloseResult.getProductTaxRate(),
                accountCloseResult.getAmountSum());
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