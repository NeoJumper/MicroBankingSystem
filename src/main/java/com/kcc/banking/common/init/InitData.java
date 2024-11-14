package com.kcc.banking.common.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.TimeZone;

//@Profile("local") // "local" 프로파일에서만 실행
@Configuration
@RequiredArgsConstructor
public class InitData {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            System.out.println(TimeZone.getDefault().getID());
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
            WHERE business_date = TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
            """;

            String interestPlsql = """
                    -- Insert 15 Interest records with monthly decreasing creation dates starting from August 1, 2024.
                    BEGIN
                        FOR i IN 1..15 LOOP
                            INSERT INTO Interest (
                                id, acc_id, registrant_id, branch_id, creation_date,
                                amount, balance, interest_rate, preferential_interest_rate, payment_status,
                                trade_number, registration_date, modifier_date, modifier_id, version
                            )
                            VALUES (
                                interest_seq.NEXTVAL,
                                '001-0000073-7373', 2, 1,
                                TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') - INTERVAL '1' MONTH * (i - 1),\s
                                300, 120000, 2.6, 0.1, 'N',
                                1, TO_TIMESTAMP('2024-07-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') - INTERVAL '1' MONTH * (i - 1),\s
                                CURRENT_TIMESTAMP, 2, 1
                            );
                        END LOOP;
                    END;
                    
                    """;

            // SQL 실행
            jdbcTemplate.execute(firstPlsql);
            jdbcTemplate.execute(secondPlsql);
            jdbcTemplate.execute(updateSql);
//            jdbcTemplate.execute(interestPlsql);
        };
    }
}
