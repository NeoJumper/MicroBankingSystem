package com.kcc.banking.domain.trade.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kcc.banking.IntegrationTest;
import com.kcc.banking.common.init.InitData;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchDTO;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import com.kcc.banking.domain.customer.service.CustomerService;
import com.kcc.banking.domain.trade.dto.request.TransferTradeCreate;
import org.apache.ibatis.mapping.Environment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

@ActiveProfiles("test") // 테스트에서 "test" 프로파일을 활성화
@SpringBootTest
class TradeRestControllerTest extends IntegrationTest {

    @Autowired
    private TradeRestController tradeRestController;

    @Autowired
    @Qualifier("applicationTaskExecutor") // 사용할 빈을 명시적으로 지정
    private AsyncTaskExecutor asyncTaskExecutor;


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
                v_end_date DATE := TRUNC(ADD_MONTHS(v_date, 3));
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
                WHILE v_start_date <= v_end_date LOOP
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
            WHERE business_date = '24/08/02'
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

    /*
    @Test
    @DisplayName("잔액 초과 예외 발생")
    @WithUserDetails(value = "2", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void test1() throws Exception {
        TransferTradeCreate transferTradeCreate = TransferTradeCreate.builder()
                .accId("001-0000004-4567")
                .accountPassword("1234")
                .description("급여이체")
                .targetAccId("001-0000007-0726")
                .transferAmount(BigDecimal.valueOf(1000000))
                .build();

        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("2");
        MvcResult mvcResult = mockMvc.perform(post("/api/employee/account-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferTradeCreate)))
                .andDo(print())
                .andExpect(status().isOk()) // 적절한 응답 상태 코드로 변경
                .andReturn();

    }

    @Test
    @DisplayName("1회 이체 한도 예외 발생")
    @WithUserDetails(value = "2", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void test2() throws Exception {
        TransferTradeCreate transferTradeCreate = TransferTradeCreate.builder()
                .accId("001-0000004-4567")
                .accountPassword("1234")
                .description("급여이체")
                .targetAccId("001-0000007-0726")
                .transferAmount(BigDecimal.valueOf(10000001))
                .build();

        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("2");
        MvcResult mvcResult = mockMvc.perform(post("/api/employee/account-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferTradeCreate)))
                .andDo(print())
                .andExpect(status().isOk()) // 적절한 응답 상태 코드로 변경
                .andReturn();

    }


    @DisplayName("1일 이체 한도 예외 발생")
    @WithUserDetails(value = "2", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void test3() throws Exception {
        TransferTradeCreate transferTradeCreate = TransferTradeCreate.builder()
                .accId("001-0000004-4567")
                .accountPassword("1234")
                .description("급여이체")
                .targetAccId("001-0000007-0726")
                .transferAmount(BigDecimal.valueOf(10000001))
                .build();

        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("2");
        MvcResult mvcResult = mockMvc.perform(post("/api/employee/account-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferTradeCreate)))
                .andDo(print())
                .andExpect(status().isOk()) // 적절한 응답 상태 코드로 변경
                .andReturn();

    }

    */

   /* @DisplayName("동시성 테스트")
    @RepeatedTest(5) // 이 테스트를 여러 번 반복하여 동시성 문제를 찾음
    //@Test
    @WithUserDetails(value = "2", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void testConcurrency() throws Exception {
        TransferTradeCreate transferTradeCreate1 = TransferTradeCreate.builder()
                .accId("001-0000004-4567")
                .accountPassword("1234")
                .description("급여이체")
                .targetAccId("001-0000007-0726")
                .transferAmount(BigDecimal.valueOf(1000000))
                .build();

        TransferTradeCreate transferTradeCreate2 = TransferTradeCreate.builder()
                .accId("001-0000004-4567")
                .accountPassword("1234")
                .description("기타이체")
                .targetAccId("001-0000007-0726")
                .transferAmount(BigDecimal.valueOf(1000000))
                .build();

        SecurityContext securityContext = SecurityContextHolder.getContext();

        CompletableFuture<MvcResult> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                MDC.put("userId", "2"); // 사용자 ID 또는 다른 정보 설정
                SecurityContextHolder.setContext(securityContext);
                return mockMvc.perform(post("/api/employee/account-transfer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transferTradeCreate1)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                SecurityContextHolder.clearContext();
            }
        }, asyncTaskExecutor);

        CompletableFuture<MvcResult> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                MDC.put("userId", "2"); // 사용자 ID 또는 다른 정보 설정
                SecurityContextHolder.setContext(securityContext);
                return mockMvc.perform(post("/api/employee/account-transfer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transferTradeCreate2)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                SecurityContextHolder.clearContext();
            }
        }, asyncTaskExecutor);
    }
*/
    @DisplayName("데드락 테스트")
    @RepeatedTest(5) // 이 테스트를 여러 번 반복하여 동시성 문제를 찾음
    //@Test
    @WithUserDetails(value = "2", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void deadlockTest() throws Exception {

        TransferTradeCreate transferTradeCreate1 = TransferTradeCreate.builder()
                .accId("001-0000004-4567")
                .accountPassword("1234")
                .description("급여이체")
                .targetAccId("001-0000005-5678")
                .transferAmount(BigDecimal.valueOf(1000000))
                .build();

        TransferTradeCreate transferTradeCreate2 = TransferTradeCreate.builder()
                .accId("001-0000005-5678")
                .accountPassword("1234")
                .description("기타이체")
                .targetAccId("001-0000004-4567")
                .transferAmount(BigDecimal.valueOf(2000000))
                .build();

        SecurityContext securityContext = SecurityContextHolder.getContext();

        CompletableFuture<MvcResult> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                MDC.put("userId", "2"); // 사용자 ID 또는 다른 정보 설정
                SecurityContextHolder.setContext(securityContext);
                return mockMvc.perform(post("/api/employee/account-transfer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transferTradeCreate1)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                SecurityContextHolder.clearContext();
            }
        }, asyncTaskExecutor);

        CompletableFuture<MvcResult> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                MDC.put("userId", "2"); // 사용자 ID 또는 다른 정보 설정
                SecurityContextHolder.setContext(securityContext);
                return mockMvc.perform(post("/api/employee/account-transfer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transferTradeCreate2)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                SecurityContextHolder.clearContext();
            }
        }, asyncTaskExecutor);


    }

    @DisplayName("데드락 테스트(비동기 X)")
    @RepeatedTest(5) // 이 테스트를 여러 번 반복하여 동시성 문제를 찾음
    //@Test
    @WithUserDetails(value = "2", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void deadlockTest2() throws Exception {

        TransferTradeCreate transferTradeCreate1 = TransferTradeCreate.builder()
                .accId("001-0000004-4567")
                .accountPassword("1234")
                .description("급여이체")
                .targetAccId("001-0000005-5678")
                .transferAmount(BigDecimal.valueOf(1000000))
                .build();

        TransferTradeCreate transferTradeCreate2 = TransferTradeCreate.builder()
                .accId("001-0000005-5678")
                .accountPassword("1234")
                .description("기타이체")
                .targetAccId("001-0000004-4567")
                .transferAmount(BigDecimal.valueOf(1000000))
                .build();


        mockMvc.perform(post("/api/employee/account-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferTradeCreate1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        mockMvc.perform(post("/api/employee/account-transfer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transferTradeCreate2)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

    }
}