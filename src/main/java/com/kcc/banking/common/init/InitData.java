package com.kcc.banking.common.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

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

            // SQL 실행
            jdbcTemplate.execute(firstPlsql);
            jdbcTemplate.execute(secondPlsql);
            jdbcTemplate.execute(updateSql);
        };
    }
}
