package com.kcc.banking.domain.trade.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kcc.banking.IntegrationTest;
import com.kcc.banking.common.init.InitData;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchDTO;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import com.kcc.banking.domain.customer.service.CustomerService;
import com.kcc.banking.domain.trade.dto.request.TransferTradeCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
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

@SpringBootTest
class TradeRestControllerTest extends IntegrationTest {

    @Autowired
    private TradeRestController tradeRestController;

    @Autowired
    private CommandLineRunner commandLineRunner;

    @BeforeEach
    void beforeEach() throws Exception {
        String ddl = new String(Files.readAllBytes(Paths.get("src/main/resources/schema.sql")));
        // 구문을 세미콜론(;)으로 분리하여 개별적으로 실행
        String[] ddlStatements = ddl.split(";");

        for (String statement : ddlStatements) {
            // 빈 구문이 아닌 경우에만 실행
            if (!statement.trim().isEmpty()) {
                jdbcTemplate.execute(statement.trim());
            }
        }

        System.out.println("DDL 구문 실행 완료");

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

        commandLineRunner.run();
        System.out.println("영업일 초기 데이터 삽입");
    }

    @Test
    @DisplayName("잔액 초과 예외 발생")
    @WithUserDetails(value = "2", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void test1() throws Exception {
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

    @Test
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


}