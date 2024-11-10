INSERT INTO Branch (id, name, address, phone_number, vault_cash)
VALUES (branch_seq.NEXTVAL, '진관동1호점', '서울시 은평구 진관동 123-45', '02-123-1234', 100000000);

-- 지점 1 매니저
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '김철수', '1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-1111-1111', 'ROLE_MANAGER', 1);

-- 지점 1 행원
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '김하늘', '1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-5678-9101', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이수진', '1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-2345-6789', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '박준호', '1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-3456-7890', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정미래', '1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-4567-8901', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '최유리', '1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-5678-9012', 'ROLE_EMPLOYEE', 1);

---------- 고객 데이터 ----------

-- 1번 고객
-- 남성
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이준호', '010-5355-4406', 'MALE', '901125-1234567', '서울특별시 강남구 역삼동 123-45', '1990-11-25',
        '2등급', 2);

-- 2번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김태훈', '010-2222-3333', 'MALE', '850615-2345678', '부산광역시 해운대구 우동 456-78', '1985-06-15',
        '2등급', 2);


-- 자율적금 단리
-- 1
INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type, interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '네오자유행복적금', 2.5, SYSDATE, '12',
        0.154, 1, 'PRIVATE', 'FLEXIBLE', 'SIMPLE');
--2
INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type, interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '네오자유청년행복적금', 3.5, SYSDATE, '12',
        0.154, 1, 'PRIVATE', 'FLEXIBLE', 'SIMPLE');


-- 자율적금 복리
--3
INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type, interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '다달이더하는자유적금', 0.4, SYSDATE, '12',
        0.154, 1, 'PRIVATE', 'FLEXIBLE', 'COMPOUND');
--4
INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type, interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '다달이행복청년자유적금', 0.6, SYSDATE, '12',
        0.154, 1, 'PRIVATE', 'FLEXIBLE', 'COMPOUND');

---------- 단리 자율 적금 생성 ----------
-- 1번 상품
INSERT INTO Account(id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                    expire_date, password, balance, account_type, open_date, status,
                    version)
VALUES ('001-0000071-7171', 1, 2, 1, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0.1, TO_TIMESTAMP('2025-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        100000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 2번 상품
INSERT INTO Account(id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                    expire_date, password, balance, account_type, open_date, status,
                    version)
VALUES ('001-0000072-7272', 1, 2, 2, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0.1, TO_TIMESTAMP('2025-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        120000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


---------- 복리 자율 적금 생성 ----------
--3번 상품
INSERT INTO Account(id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                    expire_date, password, balance, account_type, open_date, status,
                    version)
VALUES ('001-0000073-7373', 1, 2, 3, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0.1, TO_TIMESTAMP('2025-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        120000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

--4번 상품
INSERT INTO Account(id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                    expire_date, password, balance, account_type, open_date, status,
                    version)
VALUES ('001-0000074-7474', 1, 2, 4, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0.1, TO_TIMESTAMP('2025-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        140000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

