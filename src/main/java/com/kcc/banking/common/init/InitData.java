package com.kcc.banking.common.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

//@Profile("local") // "local" 프로파일에서만 실행
@Configuration
@RequiredArgsConstructor
public class InitData {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public CommandLineRunner init() {
        return args -> {

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


            // 신규 PL/SQL 스크립트: 2024년 2월 2일부터 2월 29일까지 거래 내역 삽입
            String transactionPlsql = """
            DECLARE
                v_date DATE := TO_DATE('2024-02-02', 'YYYY-MM-DD');
                v_end_date DATE := TO_DATE('2024-02-29', 'YYYY-MM-DD');
                v_num_trades INTEGER;
                v_current_balance NUMBER;
                v_new_balance NUMBER;
                v_amount NUMBER;
                v_acc_id VARCHAR2(20);
                v_target_acc_id VARCHAR2(20);
            BEGIN
                WHILE v_date <= v_end_date LOOP
                    -- 각 날짜마다 20~50개의 거래 생성
                    v_num_trades := FLOOR(DBMS_RANDOM.VALUE(20, 51));
                    DBMS_OUTPUT.PUT_LINE('날짜 ' || TO_CHAR(v_date, 'YYYY-MM-DD') || '에 ' || v_num_trades || '개의 거래를 생성합니다.');
            
                    FOR i IN 1..v_num_trades LOOP
                        BEGIN
                            -- acc_id는 거래를 발생시킬 계좌 ID로 임의 선택
                            v_acc_id := CASE MOD(i, 4)
                                        WHEN 0 THEN '001-0000061-6161'
                                        WHEN 1 THEN '001-0000062-6262'
                                        WHEN 2 THEN '001-0000063-6363'
                                        ELSE '001-0000064-6464'
                                      END;
            
                            -- 현재 잔액 조회 (출금 또는 입금)
                            SELECT balance INTO v_current_balance
                            FROM Account
                            WHERE id = v_acc_id
                            FOR UPDATE;
            
                            -- 거래 유형과 cash_indicator 설정
                            IF MOD(i, 6) IN (0, 1) THEN -- DEPOSIT 또는 WITHDRAWAL
                                -- 랜덤 금액 설정 (100,000 ~ 1,000,000)
                                v_amount := FLOOR(DBMS_RANDOM.VALUE(100000, 1000001));
            
                                -- cash_indicator 랜덤 설정
                                DECLARE
                                    v_cash_indicator VARCHAR2(5) := CASE MOD(i, 2)
                                                                    WHEN 0 THEN 'TRUE'
                                                                    ELSE 'FALSE'
                                                                  END;
                                BEGIN
                                    IF v_cash_indicator = 'TRUE' THEN
                                        -- 현금 입금/출금 처리
                                        IF MOD(i, 3) = 0 THEN -- DEPOSIT
                                            v_new_balance := v_current_balance + v_amount;
                                            -- 잔액 업데이트
                                            UPDATE Account
                                            SET balance = v_new_balance
                                            WHERE id = v_acc_id;
            
                                            -- TRADE 삽입 (DEPOSIT)
                                            INSERT INTO TRADE (
                                                id,
                                                acc_id,
                                                registrant_id,
                                                branch_id,
                                                trade_date,
                                                amount,
                                                balance,
                                                trade_type,
                                                status,
                                                cash_indicator,
                                                description,
                                                trade_number
                                            ) VALUES (
                                                trade_seq.NEXTVAL,
                                                v_acc_id,
                                                CASE MOD(i, 6)
                                                    WHEN 0 THEN 2
                                                    WHEN 1 THEN 3
                                                    WHEN 2 THEN 4
                                                    WHEN 3 THEN 5
                                                    WHEN 4 THEN 6
                                                    ELSE 2
                                                END,
                                                1, -- branch_id을 항상 1로 설정
                                                TO_TIMESTAMP('2024-02-' || LPAD(TO_CHAR(v_date, 'DD'), 2, '0') || ' ' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(8, 18)), 2, '0') || ':' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(0, 60)), 2, '0') || ':' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(0, 60)), 2, '0'), 'YYYY-MM-DD HH24:MI:SS'),
                                                v_amount,
                                                v_new_balance,
                                                'DEPOSIT',
                                                'NOR', -- 상태는 정상이므로 'NOR'로 설정
                                                'TRUE', -- cash_indicator
                                                '현금 입금',
                                                trade_num_seq.NEXTVAL
                                            );
                                        ELSIF MOD(i, 3) = 1 THEN -- WITHDRAWAL
                                            -- WITHDRAWAL 시 잔액이 음수가 되지 않도록 체크
                                            IF v_current_balance - v_amount < 0 THEN
                                                v_new_balance := 0;
                                            ELSE
                                                v_new_balance := v_current_balance - v_amount;
                                            END IF;
                                           \s
                                            -- 잔액 업데이트
                                            UPDATE Account
                                            SET balance = v_new_balance
                                            WHERE id = v_acc_id;
            
                                            -- TRADE 삽입 (WITHDRAWAL)
                                            -- TRADE 삽입 (WITHDRAWAL)
                                            INSERT INTO TRADE (
                                                id,
                                                acc_id,
                                                registrant_id,
                                                branch_id,
                                                trade_date,
                                                amount,
                                                balance,
                                                trade_type,
                                                status,
                                                cash_indicator,
                                                description,
                                                trade_number
                                            ) VALUES (
                                                trade_seq.NEXTVAL,
                                                v_acc_id,
                                                CASE MOD(i, 6)
                                                    WHEN 0 THEN 2
                                                    WHEN 1 THEN 3
                                                    WHEN 2 THEN 4
                                                    WHEN 3 THEN 5
                                                    WHEN 4 THEN 6
                                                    ELSE 2
                                                END,
                                                1, -- branch_id을 항상 1로 설정
                                                TO_TIMESTAMP('2024-02-' || LPAD(TO_CHAR(v_date, 'DD'), 2, '0') || ' ' ||
                                                             LPAD(FLOOR(DBMS_RANDOM.VALUE(8, 18)), 2, '0') || ':' ||
                                                             LPAD(FLOOR(DBMS_RANDOM.VALUE(0, 60)), 2, '0') || ':' ||
                                                             LPAD(FLOOR(DBMS_RANDOM.VALUE(0, 60)), 2, '0'), 'YYYY-MM-DD HH24:MI:SS'),
                                                v_amount,
                                                v_new_balance,
                                                'WITHDRAWAL',
                                                'NOR', -- 상태는 정상이므로 'NOR'로 설정
                                                'TRUE', -- Added 'cash_indicator' value
                                                '현금 출금',
                                                trade_num_seq.NEXTVAL
                                            );
                                            
                                        END IF;
                                    ELSE
                                        -- 이체 처리 (cash_indicator = 'FALSE')
                                        -- 대상 계좌 선택 (임의로 다른 계좌 선택)
                                        v_target_acc_id := CASE MOD(i, 4)
                                                            WHEN 0 THEN '001-0000062-6262'
                                                            WHEN 1 THEN '001-0000063-6363'
                                                            WHEN 2 THEN '001-0000064-6464'
                                                            ELSE '001-0000061-6161'
                                                          END;
            
                                        -- 대상 계좌의 현재 잔액 조회
                                        DECLARE
                                            v_target_balance NUMBER;
                                        BEGIN
                                            SELECT balance INTO v_target_balance
                                            FROM Account
                                            WHERE id = v_target_acc_id
                                            FOR UPDATE;
            
                                            -- 거래 금액 설정 (랜덤 금액: 100,000 ~ 1,000,000)
                                            v_amount := FLOOR(DBMS_RANDOM.VALUE(100000, 1000001));
            
                                            -- 출금 계좌 잔액 업데이트
                                            IF v_current_balance - v_amount < 0 THEN
                                                v_new_balance := 0;
                                            ELSE
                                                v_new_balance := v_current_balance - v_amount;
                                            END IF;
            
                                            UPDATE Account
                                            SET balance = v_new_balance
                                            WHERE id = v_acc_id;
            
                                            -- 대상 계좌 잔액 업데이트
                                            v_new_balance := v_target_balance + v_amount;
                                            UPDATE Account
                                            SET balance = v_new_balance
                                            WHERE id = v_target_acc_id;
            
                                            -- TRADE 삽입 (출금)
                                            INSERT INTO TRADE (
                                                id,
                                                acc_id,
                                                registrant_id,
                                                branch_id,
                                                trade_date,
                                                amount,
                                                balance,
                                                trade_type,
                                                status,
                                                cash_indicator,
                                                description,
                                                trade_number
                                            ) VALUES (
                                                trade_seq.NEXTVAL,
                                                v_acc_id,
                                                CASE MOD(i, 6)
                                                    WHEN 0 THEN 2
                                                    WHEN 1 THEN 3
                                                    WHEN 2 THEN 4
                                                    WHEN 3 THEN 5
                                                    WHEN 4 THEN 6
                                                    ELSE 2
                                                END,
                                                1, -- branch_id을 항상 1로 설정
                                                TO_TIMESTAMP('2024-02-' || LPAD(TO_CHAR(v_date, 'DD'), 2, '0') || ' ' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(8, 18)), 2, '0') || ':' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(0, 60)), 2, '0') || ':' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(0, 60)), 2, '0'), 'YYYY-MM-DD HH24:MI:SS'),
                                                v_amount,
                                                v_new_balance,
                                                'WITHDRAWAL',
                                                'NOR', -- 상태는 정상이므로 'NOR'로 설정
                                                'FALSE', -- cash_indicator = FALSE
                                                '이체 출금',
                                                trade_num_seq.NEXTVAL
                                            );
            
                                            -- TRADE 삽입 (입금)
                                            INSERT INTO TRADE (
                                                id,
                                                acc_id,
                                                registrant_id,
                                                branch_id,
                                                trade_date,
                                                amount,
                                                balance,
                                                trade_type,
                                                status,
                                                cash_indicator,
                                                description,
                                                trade_number
                                            ) VALUES (
                                                trade_seq.NEXTVAL,
                                                v_target_acc_id,
                                                CASE MOD(i, 6)
                                                    WHEN 0 THEN 2
                                                    WHEN 1 THEN 3
                                                    WHEN 2 THEN 4
                                                    WHEN 3 THEN 5
                                                    WHEN 4 THEN 6
                                                    ELSE 2
                                                END,
                                                1, -- branch_id을 항상 1로 설정
                                                TO_TIMESTAMP('2024-02-' || LPAD(TO_CHAR(v_date, 'DD'), 2, '0') || ' ' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(8, 18)), 2, '0') || ':' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(0, 60)), 2, '0') || ':' ||\s
                                                            LPAD(FLOOR(DBMS_RANDOM.VALUE(0, 60)), 2, '0'), 'YYYY-MM-DD HH24:MI:SS'),
                                                v_amount,
                                                v_new_balance,
                                                'DEPOSIT',
                                                'NOR', -- 상태는 정상이므로 'NOR'로 설정
                                                'FALSE', -- cash_indicator = FALSE
                                                '이체 입금',
                                                trade_num_seq.NEXTVAL
                                            );
                                        END;
                                    END IF;
                                END;
                            END IF;
                        EXCEPTION
                            WHEN OTHERS THEN
                                DBMS_OUTPUT.PUT_LINE('거래 삽입 오류: ' || SQLERRM);
                        END;
                    END LOOP;
                    v_date := v_date + 1;
                END LOOP;
            END;

            """;

            // SQL 실행
            jdbcTemplate.execute(firstPlsql);
            jdbcTemplate.execute(secondPlsql);
            jdbcTemplate.execute(updateSql);
            jdbcTemplate.execute(transactionPlsql);
        };
    }
}
