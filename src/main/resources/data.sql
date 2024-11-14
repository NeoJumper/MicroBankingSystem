INSERT INTO Branch (id, name, address, phone_number, vault_cash)
VALUES (0, '0', '0', '0', 0);

INSERT INTO Branch (id, name, address, phone_number, vault_cash)
VALUES (branch_seq.NEXTVAL, '진관동1호점', '서울시 은평구 진관동 123-45', '02-123-1234', 100000000);

INSERT INTO Branch (id, name, address, phone_number, vault_cash)
VALUES (branch_seq.NEXTVAL, '서초동2호점', '서울시 서초구 서초동 678-90', '02-234-5678', 100000000);

INSERT INTO Branch (id, name, address, phone_number, vault_cash)
VALUES (branch_seq.NEXTVAL, '마포동3호점', '서울시 마포구 마포동 345-67', '02-345-6789', 100000000);

INSERT INTO Branch (id, name, address, phone_number, vault_cash)
VALUES (branch_seq.NEXTVAL, '성수동4호점', '서울시 성동구 성수동 890-12', '02-456-7890', 100000000);

INSERT INTO Branch (id, name, address, phone_number, vault_cash)
VALUES (branch_seq.NEXTVAL, '강남동5호점', '서울시 강남구 강남동 234-56', '02-567-8901', 100000000);

-- 지점 1 매니저
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '김철수', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-1111-1111', 'ROLE_MANAGER', 1);

-- 지점 1 행원
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '김하늘', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-5678-9101', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이수진', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-2345-6789', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '박준호', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-3456-7890', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정미래', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-4567-8901', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '최유리', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-5678-9012', 'ROLE_EMPLOYEE', 1);

-- 지점 2 매니저
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '박영희', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-2222-2222', 'ROLE_MANAGER', 2);

-- 지점 2 행원
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '임도현', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-6789-0123', 'ROLE_EMPLOYEE', 2);


-- 지점 3 매니저
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이민호', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-3333-3333', 'ROLE_MANAGER', 3);
-- 지점 3 행원
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '홍서연', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-7890-1234', 'ROLE_EMPLOYEE', 3);


-- 지점 4 매니저
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '최지훈', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-4444-4444', 'ROLE_MANAGER', 4);
-- 지점 4 행원
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정세진', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-8901-2345', 'ROLE_EMPLOYEE', 4);


-- 지점 5 매니저
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정유진', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-5555-5555', 'ROLE_MANAGER', 5);

-- 지점 5 행원
INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '민정호', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-9012-3456', 'ROLE_EMPLOYEE', 5);

INSERT INTO Employee (id, name, birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이경호', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        'test1@gmail.com', '010-0123-4567', 'ROLE_EMPLOYEE', 5);

---------- 고객 데이터 ----------
/*
    고객번호 1~5: 지점 1에서 가입
    고객번호 6~16: 지점 순차적 가입
        - 각 지점의 행원 번호로 맞춰서 작성
    ex)
    customer_id - branch_id
    6 - 2
    7 - 2
    8 - 2
    9 - 2
    10 - 3
    11 - 3
    12 - 3
    13 - 3
    14 - 4
    15 - 4
    16 - 4

    고객번호 17~20: 지점 순차적 가입
        - 17~20번 고객은 임시로 "법인계좌" 를 위한 고객으로 작성
    ex)
    17 - 1
    18 - 2
    19 - 3
    20 - 4

    고객번호 21~30: 회사(17) 의 직원 10명
        - 지점 1에서 가입

    고객번호 31~40: 회사(18) 의 직원 10명
        - 지점 2에서 가입

    고객번호 41~51: 회사(19)의 직원 10명
        - 지점 3에서 가입

    고객번호 51~60: 회사(20)의 직원 10명

 */

-- 1번 고객
-- 남성
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이준호', '010-5355-4406', 'MALE', '901125-1234567', '서울특별시 강남구 역삼동 123-45', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 2);

-- 2번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김태훈', '010-2222-3333', 'MALE', '850615-2345678', '부산광역시 해운대구 우동 456-78', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 2);

-- 3번 고객
-- 여성
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박지연', '010-3333-4444', 'FEMALE', '920301-3456789', '대구광역시 수성구 범어동 234-56',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 2);

-- 4번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정은주', '010-4444-5555', 'FEMALE', '971215-4567890', '인천광역시 남동구 구월동 567-89',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 2);

-- 5번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '최민아', '010-5555-6666', 'FEMALE', '880110-5678901', '경기도 성남시 분당구 정자동 789-12',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 2);

-- 6번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '김태민', '010-2345-6789', 'MALE', '890123-1234567', '서울특별시 강남구 논현동 123-45', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 8);

-- 7번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '박지윤', '010-3456-7890', 'FEMALE', '760204-2345678', '서울특별시 송파구 가락동 678-90',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 8);

-- 8번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '이수민', '010-4567-8901', 'FEMALE', '950310-3456789', '서울특별시 서초구 반포동 345-67',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 8);

-- 9번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '정하늘', '010-5678-9012', 'MALE', '880521-4567890', '서울특별시 동작구 상도동 890-12', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 8);

-- 10번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '최민호', '010-6789-0123', 'MALE', '720606-5678901', '서울특별시 마포구 상암동 123-45', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 10);

-- 11번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '강영희', '010-7890-1234', 'FEMALE', '990417-6789012', '서울특별시 강북구 수유동 345-67',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);

-- 12번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '김하늘', '010-8901-2345', 'FEMALE', '880724-7890123', '서울특별시 중구 명동 456-78', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 10);

-- 13번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '이동훈', '010-9012-3456', 'MALE', '930512-8901234', '서울특별시 영등포구 신길동 567-89',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);

-- 14번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '박준호', '010-0123-4567', 'MALE', '860730-9012345', '서울특별시 관악구 봉천동 678-90', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 12);

-- 15번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '정미래', '010-1234-5678', 'FEMALE', '950812-0123456', '서울특별시 용산구 이태원동 789-12',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 12);

-- 16번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '이현정', '010-2345-6789', 'FEMALE', '880305-1234567', '서울특별시 서대문구 연희동 890-23',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 12);

---------- 상품 생성 ----------
/*
    지점번호: 순차적
    id 1~7: 보통예금
    id 8~12 : 정기적금
 */

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '청년안심보통예금', 2.5, SYSDATE, '00', 0.154, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 2, '청년미래보통예금', 2.5, SYSDATE, '00', 0.154, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 3, '노후든든보통예금', 2.5, SYSDATE, '00', 0.154, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 4, '노후건강보통예금', 2.5, SYSDATE, '00', 0.154, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 5, '더편한보통예금', 2.5, SYSDATE, '00', 0.154, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '기업보통예금', 0.1, SYSDATE, '00', 0.154, 1, 'CORPORATION');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, 'ONE 사업자 통장', 0.1, SYSDATE, '00', 0.154, 1, 'CORPORATION');

--적금 상품 8 - 12번

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type,interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '6개월만기정기적금', 2.5, SYSDATE, '06', 0.154, 1, 'PRIVATE', 'FIXED','SIMPLE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type,interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '1년만기정기적금', 3, SYSDATE, '12', 0.154, 1, 'PRIVATE', 'FIXED','SIMPLE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type,interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '2년만기정기적금', 3.5, SYSDATE, '24', 0.154, 1, 'PRIVATE', 'FIXED','SIMPLE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type,interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '3년만기정기적금', 3.5, SYSDATE, '36', 0.154, 1, 'PRIVATE', 'FIXED','SIMPLE');

-- 자율적금 단리
-- 12
INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type, interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '네오단리적금', 2.5, SYSDATE, '12',
        0.154, 1, 'PRIVATE', 'FLEXIBLE', 'SIMPLE');
--13
INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type, interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '네오단리적금', 3.5, SYSDATE, '12',
        0.154, 1, 'PRIVATE', 'FLEXIBLE', 'SIMPLE');


-- 자율적금 복리
--14
INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type, interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '다달이더한자유적금', 4.5, SYSDATE, '12',
        0.154, 1, 'PRIVATE', 'FLEXIBLE', 'COMPOUND');
--15
INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type,
                     product_type, interest_calculation_method)
VALUES (product_seq.NEXTVAL, 1, '다달이행복자유적금', 0.6, SYSDATE, '12',
        0.154, 1, 'PRIVATE', 'FLEXIBLE', 'COMPOUND');

---------- 단리 자율 적금 생성 ----------
-- 12번 상품 가입 계좌
INSERT INTO Account(id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                    expire_date, password, balance, account_type, open_date, status,
                    version)
VALUES ('001-0000071-7171', 1, 5, 12, 2, TO_TIMESTAMP('2024-06-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0.1, TO_TIMESTAMP('2025-06-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
        100000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 13번 상품 가입 계좌
INSERT INTO Account(id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                        expire_date, password, balance, account_type, open_date, status,
                        version)
VALUES ('001-0000072-7272', 1, 3, 13, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
    0.1, TO_TIMESTAMP('2025-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
    '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
    120000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


---------- 복리 자율 적금 생성 ----------
--14번 상품 가입 계좌
INSERT INTO Account(id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                    expire_date, password, balance, account_type, open_date, status,
                    version)
VALUES ('001-0000073-7373', 1, 2, 14, 2, TO_TIMESTAMP('2023-06-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
    0.1, TO_TIMESTAMP('2025-06-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
    '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
    120000, 'PRIVATE', TO_TIMESTAMP('2024-06-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);



--15번 상품 가입 계좌
INSERT INTO Account(id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                    expire_date, password, balance, account_type, open_date, status,
                    version)
VALUES ('001-0000074-7474', 1, 4, 14, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
    0.1, TO_TIMESTAMP('2025-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
    '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6',
    140000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

---------- 계좌 생성 ----------
-- 지점 1
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000001-1234', 1, 1, 1, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 250000, 5000000, 10000000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000002-2345', 1, 2, 2, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.7, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 200000, 5000000, 10000000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000003-3456', 1, 3, 3, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1.0, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 150000, 5000000, 10000000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000004-4567', 1, 4, 4, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.3, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, 5000000, 10000000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000005-5678', 1, 5, 5, 2, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, 5000000, 10000000, 'PRIVATE', TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);


-- 적금계좌 개설 전용(1번 고객 통일)

-----------------    적금 관련 더미      ----------------------

-- 적금 계좌 생성
-- 가입된 적금 확인용 (2024/3/3가입)
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id,
                     start_date, preferential_interest_rate,
                     expire_date,expected_expire_date,

                     password, balance, account_type, open_date, status, version)
VALUES ('001-0000013-3687', 1, 1, 9, 2,
        TO_TIMESTAMP('2023-07-30 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1.0,
        NULL, (TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')),
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1000000, 'PRIVATE',
        TO_TIMESTAMP('2023-07-30 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 적금전용 자동이체 출금통장
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000015-7777', 1, 1, 2, 2, TO_TIMESTAMP('2023-01-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1.0, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 100000000, 5000000, 10000000, 'PRIVATE',
        TO_TIMESTAMP('2024-01-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 1회차 자동이체 설정 (고객 ID: 1)
INSERT INTO Auto_transfer (id, acc_id, target_acc_id, amount,
                           auto_transfer_start_date,
                           auto_transfer_end_date, auto_transfer_period,
                           create_date, registration_date, registrant_id,
                           modification_date, modifier_id, version)
VALUES (AUTO_TRANSFER_SEQ.nextval, '001-0000015-7777', '001-0000013-3687', 1000000,
        TO_TIMESTAMP('2023-04-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('2024-04-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1,
        SYSTIMESTAMP, SYSTIMESTAMP, 2,
        NULL, NULL, 1);

-- 1번 고객 적금 계좌 (9번 상품)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000013-3687', 2, 1, TO_TIMESTAMP('2023-03-05 00:00:01', 'YYYY-MM-DD HH24:MI:SS'),
        1000000, 1000000, 'OPEN', 'NOR', 'FALSE', '적금계좌개설', trade_num_seq.NEXTVAL, '001-0000015-7777');


-- 계좌 가입 내역

-- 1번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        250000, 250000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 2번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000002-2345', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        200000, 200000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 3번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000003-3456', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        150000, 150000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 4번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000004-4567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        6000000, 6000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 5번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        300000, 300000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 계좌 이체

-----------------    1번 고객 계좌 -> 2번 고객 계좌      ----------------------
-- 1번 고객 계좌 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 200000, 'WITHDRAWAL', 'NOR', 'FALSE', '9월 용돈', trade_num_seq.NEXTVAL, '001-0000002-2345');

-- 2번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000002-2345', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 250000, 'DEPOSIT', 'NOR', 'FALSE', '9월 용돈', trade_num_seq.CURRVAL, '001-0000001-1234');


-----------------    1번 고객 계좌 -> 3번 고객 계좌      ----------------------


-- 1번 고객 계좌 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 150000, 'WITHDRAWAL', 'NOR', 'FALSE', '명절 선물', trade_num_seq.NEXTVAL, '001-0000003-3456');

-- 3번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000003-3456', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 200000, 'DEPOSIT', 'NOR', 'FALSE', '명절 선물', trade_num_seq.CURRVAL, '001-0000001-1234');

----------------     5번 고객 계좌 -> 1번 고객     -------------------------

-- 5번 고객 계좌 출금 --
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000005-5678', 5, 5, TO_TIMESTAMP('2024-08-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'),
        30000, 270000, 'WITHDRAWAL', 'NOR', 'FALSE', '깜짝 선물', trade_num_seq.NEXTVAL, '001-0000001-1234');

-- 1번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 5, 5, TO_TIMESTAMP('2024-08-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'),
        30000, 130000, 'DEPOSIT', 'NOR', 'FALSE', '깜짝 선물', trade_num_seq.CURRVAL, '001-0000005-5678');


-- 계좌 현금 입출금

-- 1번 계좌로 5,000원 현금 입금
INSERT INTO Trade (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.NEXTVAL, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 5000,
        135000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

--2번 계좌로 5,000원 현금 입금
INSERT INTO Trade (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.NEXTVAL, '001-0000002-2345', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 5000,
        255000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

-- 4번 계좌에서 50,000원 현금 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000004-4567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 150000, 'WITHDRAWAL', 'NOR', 'TRUE', '생활비 인출', trade_num_seq.NEXTVAL);

-- 5번 계좌에서 50,000원 현금 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 250000, 'WITHDRAWAL', 'NOR', 'TRUE', '복권 구매', trade_num_seq.NEXTVAL);


-- 계좌 해지

-- 1번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        135000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', trade_num_seq.NEXTVAL);

-- 2번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000002-2345', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        255000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', trade_num_seq.NEXTVAL);

-- 3번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000003-3456', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        200000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', trade_num_seq.NEXTVAL);


-- 계좌 상태(해지) 및 잔액 업데이트
UPDATE Account
SET status      = 'CLS',
    balance     = 0,
    expire_date = TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS')

WHERE id IN ('001-0000001-1234', '001-0000002-2345', '001-0000003-3456');

UPDATE Account
SET balance = 12000000
WHERE id = '001-0000004-4567';

UPDATE Account
SET balance = 220000
WHERE id = '001-0000005-5678';

-- 행원 마감

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 1, 'CLOSED', 10000000, 1210000, 100000, 11110000, trade_num_seq.NEXTVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 3, 1, 'CLOSED', 10000000, 0, 0, 10000000, trade_num_seq.CURRVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 4, 1, 'CLOSED', 10000000, 0, 0, 10000000, trade_num_seq.CURRVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 5, 1, 'CLOSED', 10000000, 0, 0, 10000000, trade_num_seq.CURRVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 6, 1, 'CLOSED', 10000000, 0, 0, 10000000, trade_num_seq.CURRVAL, 1);

-- 매니저 마감
INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1, 'CLOSED', 1000000, 0, 0, 0, trade_num_seq.CURRVAL,1);


-- 거래번호 1: 지점 마감
INSERT INTO BRANCH_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, vault_cash, trade_number,
                            version)
VALUES (TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1, 'CLOSED', 60000000, 61110000, trade_num_seq.CURRVAL, 1);


INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 1, 'OPEN', 11110000, 0, 590000, null, trade_num_seq.NEXTVAL, 1);

-- 5번 계좌에서 50,000원 현금 출금
-- 8월 2일
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 3, 1, TO_TIMESTAMP('2024-08-02 00:00:07', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 200000, 'WITHDRAWAL', 'NOR', 'TRUE', '복권 구매', trade_num_seq.NEXTVAL);

-- 5번 계좌에서 100,000원 현금 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 3, 1, TO_TIMESTAMP('2024-08-02 00:00:07', 'YYYY-MM-DD HH24:MI:SS'),
        100000, 300000, 'DEPOSIT', 'NOR', 'TRUE', '일당 입금', trade_num_seq.NEXTVAL);



INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 3, 1, 'OPEN', 10000000, 100000, 50000, null, trade_num_seq.CURRVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 4, 1, 'CLOSED', 10000000, 0, 0, 10000000, trade_num_seq.CURRVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 5, 1, 'CLOSED', 10000000, 0, 0, 10000000, trade_num_seq.CURRVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit,
                              total_withdrawal, vault_cash, trade_number, version)
VALUES (TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 6, 1, 'CLOSED', 10000000, 0, 0, 10000000, trade_num_seq.CURRVAL, 1);

-- 거래번호 2: 지점 마감
INSERT INTO BRANCH_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, vault_cash, trade_number,
                            version)
VALUES (TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 1, 'OPEN', 61110000, null, trade_num_seq.CURRVAL, 1);

-- 매니저 마감
INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, trade_number, version)
VALUES(TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1, 'OPEN',  1000000, 0, 0, trade_num_seq.CURRVAL, 1);


-- 마감 행원간 현금 거래
-- 매니저 -> 행원 3번에게 50만원 인도
INSERT INTO Cash_exchange (id, registrant_id, emp_id, branch_id, amount, emp_cash_balance, manager_cash_balance, exchange_type, exchange_date ,registration_date, version)
VALUES (cash_exchange_seq.NEXTVAL, 1, 3, 1, 500000, 6000000, 500000, 'HANDOVER', TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);

-- 행원 업데이트
UPDATE EMPLOYEE_CLOSING SET total_deposit=600000, modifier_id = 1, modification_date=TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), version = version + 1   WHERE registrant_id = 3 AND closing_date = TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS');
-- 매니저 업데이트
UPDATE EMPLOYEE_CLOSING SET total_withdrawal=500000, modifier_id = 1, modification_date=TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), version = version + 1   WHERE registrant_id = 1 AND closing_date = TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS');

-- 3번 매니저 마감
UPDATE EMPLOYEE_CLOSING SET status='CLOSED',vault_cash=10550000, modifier_id = 1,registration_date=TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), modification_date =TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), version = version + 1   WHERE registrant_id = 3 AND closing_date = TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS');

---------- [ 추가 SQL문 ] ----------


/*
    [고객 생성]
    고객번호 21~30: 회사(17번 고객) 의 직원 10명
        - 지점 1에서 가입

    고객번호 31~40: 회사(18번 고객) 의 직원 10명
        - 지점 2에서 가입

    고객번호 41~51: 회사(19번 고객)의 직원 10명
        - 지점 3에서 가입

    고객번호 51~60: 회사(20번 고객)의 직원 10명

    */

---------- 법인 계좌 ----------

-- 17번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '(주)DataSphere', '02-3456-7890', 'NONE', '000001-000001', '서울특별시 종로구 혜화동 901-34',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 4);

-- 18번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, 'CodeWave Company', '02-4567-8901', 'NONE', '000001-000002', '서울특별시 종로구 무악동 012-45',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 8);

-- 19번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '(주)InnoSoft', '02-4568-8901', 'NONE', '000001-000003', '서울특별시 종로구 가회동 012-46',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);

-- 20번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '(주)TechNova', '02-4569-8901', 'NONE', '000001-000004', '서울특별시 서대문구 연희동 012-46',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 12);



---------- 1번 회사 사원 : 1번 지점 개설 등록----------


-- 21번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김민수', '010-6666-7777', 'MALE', '950101-1234567', '서울특별시 강남구 삼성동 101-1', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 3);

-- 22번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이영희', '010-7777-8888', 'FEMALE', '930202-2345678', '서울특별시 서초구 서초동 202-2',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 5);

-- 23번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박준형', '010-8888-9999', 'MALE', '900303-3456789', '서울특별시 송파구 잠실동 303-3', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 2);

-- 24번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '최수진', '010-9999-0000', 'FEMALE', '960404-4567890', '서울특별시 강동구 천호동 404-4',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 4);

-- 25번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '홍길동', '010-0000-1111', 'MALE', '880505-5678901', '서울특별시 노원구 상계동 505-5', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 6);

-- 26번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '신지훈', '010-1010-1111', 'MALE', '920606-6789012', '서울특별시 중랑구 면목동 606-6', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 4);

-- 27번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '오수민', '010-1212-1313', 'FEMALE', '970707-7890123', '서울특별시 성북구 길음동 707-7',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 5);

-- 28번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '강민호', '010-1414-1515', 'MALE', '850808-8901234', '서울특별시 은평구 불광동 808-8', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 2);

-- 29번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '윤서연', '010-1616-1717', 'FEMALE', '940909-9012345', '서울특별시 마포구 합정동 909-9',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 6);

-- 30번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정현우', '010-1818-1919', 'MALE', '910101-0123456', '서울특별시 동대문구 이문동 101-1', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 3);



---------- 2번 회사 사원 : 2번 지점 등록 ----------


-- 31번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '김지훈', '010-2121-2222', 'MALE', '970808-1234567', '서울특별시 강남구 도곡동 101-1', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 8);

-- 32번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '박수민', '010-2323-2424', 'FEMALE', '850909-2345678', '서울특별시 서초구 방배동 202-2',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 8);

-- 33번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '이현우', '010-2525-2626', 'MALE', '900101-3456789', '서울특별시 송파구 문정동 303-3', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 8);

-- 34번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '정민서', '010-2727-2828', 'FEMALE', '940202-4567890', '서울특별시 강동구 둔촌동 404-4',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 8);

-- 35번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '최민준', '010-2929-3030', 'MALE', '920303-5678901', '서울특별시 노원구 중계동 505-5', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 8);

-- 36번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '김서연', '010-3131-3232', 'FEMALE', '870404-6789012', '서울특별시 중랑구 상봉동 606-6',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 8);

-- 37번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '장동현', '010-3333-3434', 'MALE', '890505-7890123', '서울특별시 성북구 종암동 707-7', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 8);

-- 38번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '한지민', '010-3535-3636', 'FEMALE', '950606-8901234', '서울특별시 은평구 응암동 808-8',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 8);

-- 39번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '서준혁', '010-3737-3838', 'MALE', '930707-9012345', '서울특별시 마포구 서교동 909-9', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 8);

-- 40번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '신예은', '010-3939-4040', 'FEMALE', '910808-0123456', '서울특별시 동대문구 회기동 101-1',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 8);



---------- 3번 회사 사원 : 3번 지점 등록 ----------


-- 41번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '김하늘', '010-4141-4242', 'FEMALE', '910101-1234567', '서울특별시 강남구 역삼동 101-1',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);

-- 42번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '박준호', '010-4343-4444', 'MALE', '920202-2345678', '서울특별시 서초구 반포동 202-2', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 10);

-- 43번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '이수민', '010-4545-4646', 'FEMALE', '930303-3456789', '서울특별시 송파구 가락동 303-3',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);

-- 44번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '정민준', '010-4747-4848', 'MALE', '940404-4567890', '서울특별시 강동구 명일동 404-4', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 10);

-- 45번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '최서연', '010-4949-5050', 'FEMALE', '950505-5678901', '서울특별시 노원구 공릉동 505-5',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);

-- 46번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '김현우', '010-5151-5252', 'MALE', '960606-6789012', '서울특별시 중랑구 묵동 606-6', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 10);

-- 47번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '장예은', '010-5353-5454', 'FEMALE', '970707-7890123', '서울특별시 성북구 석관동 707-7',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);

-- 48번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '한지훈', '010-5555-5656', 'MALE', '980808-8901234', '서울특별시 은평구 신사동 808-8', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 10);

-- 49번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '서민지', '010-5757-5858', 'FEMALE', '990909-9012345', '서울특별시 마포구 연남동 909-9',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);

-- 50번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '신동현', '010-5959-6060', 'MALE', '000101-0123456', '서울특별시 동대문구 청량리동 101-1',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 10);



---------- 4번 회사 사원 : 4번 지점 등록 ----------


-- 51번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '김예준', '010-6161-6262', 'MALE', '910101-1234567', '서울특별시 강남구 청담동 101-1', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 12);

-- 52번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '이서연', '010-6363-6464', 'FEMALE', '920202-2345678', '서울특별시 서초구 잠원동 202-2',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 12);

-- 53번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '박민준', '010-6565-6666', 'MALE', '930303-3456789', '서울특별시 송파구 방이동 303-3', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 12);

-- 54번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '최지민', '010-6767-6868', 'FEMALE', '940404-4567890', '서울특별시 강동구 상일동 404-4',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 12);

-- 55번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '정현우', '010-6969-7070', 'MALE', '950505-5678901', '서울특별시 노원구 월계동 505-5', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 12);

-- 56번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '김수민', '010-7171-7272', 'FEMALE', '960606-6789012', '서울특별시 중랑구 면목동 606-6',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 12);

-- 57번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '이준호', '010-7373-7474', 'MALE', '970707-7890123', '서울특별시 성북구 장위동 707-7', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 12);

-- 58번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '박서현', '010-7575-7676', 'FEMALE', '980808-8901234', '서울특별시 은평구 역촌동 808-8',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 12);

-- 59번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '최민수', '010-7777-7878', 'MALE', '990909-9012345', '서울특별시 마포구 도화동 909-9', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        '2등급', 12);

-- 60번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '정예은', '010-7979-8080', 'FEMALE', '000101-0123456', '서울특별시 동대문구 답십리동 101-1',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), '2등급', 12);

-- 추후 등록일 / 등록자 / 수정일 / 수정자 각 INSERT 와 UPDATE 문에 맞춰서 작성 예정


-- 통계용 더미
-- 61번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      registrant_id, security_level)
VALUES (customer_seq.NEXTVAL, 1, '김태희', '010-8080-9090', 'FEMALE', '881010-1234567', '서울특별시 강남구 삼성동 101-1',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), 2, '2등급');

-- 62번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      registrant_id, security_level)
VALUES (customer_seq.NEXTVAL, 1, '이민호', '010-8181-9191', 'MALE', '891011-2345678', '서울특별시 강남구 역삼동 202-2', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        3, '2등급');

-- 63번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      registrant_id, security_level)
VALUES (customer_seq.NEXTVAL, 1, '박보검', '010-8282-9292', 'MALE', '901012-3456789', '서울특별시 강남구 논현동 303-3', TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'),
        4, '2등급');

-- 64번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date,
                      registrant_id, security_level)
VALUES (customer_seq.NEXTVAL, 1, '최지우', '010-8383-9393', 'FEMALE', '911013-4567890', '서울특별시 강남구 선릉동 404-4',
        TO_TIMESTAMP('1999-02-14', 'YYYY-MM-DD'), 5, '2등급');


-- [계좌 생성: 고객 61번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password,account_type, balance,per_trade_limit, daily_limit,  open_date, status, version)
VALUES ('001-0000061-6161', 1, 61, 3, 2, TO_TIMESTAMP('2024-02-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','PRIVATE', 1000000,5000000, 10000000,
        TO_TIMESTAMP('2024-02-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 62번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password,account_type, balance,per_trade_limit, daily_limit, open_date, status, version)
VALUES ('001-0000062-6262', 1, 62, 2, 3, TO_TIMESTAMP('2024-02-01 09:05:00', 'YYYY-MM-DD HH24:MI:SS'), 0.3, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'PRIVATE',1500000,5000000, 10000000,
        TO_TIMESTAMP('2024-02-01 09:05:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 63번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password,account_type, balance,per_trade_limit, daily_limit, open_date, status, version)
VALUES ('001-0000063-6363', 1, 63, 4, 4, TO_TIMESTAMP('2024-02-01 09:10:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','PRIVATE', 2000000,5000000, 10000000,
        TO_TIMESTAMP('2024-02-01 09:10:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 64번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password,account_type, balance,per_trade_limit, daily_limit, open_date, status, version)
VALUES ('001-0000064-6464', 1, 64, 1, 5, TO_TIMESTAMP('2024-02-01 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 0.2, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','PRIVATE', 500000,5000000, 10000000,
        TO_TIMESTAMP('2024-02-01 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


-- 61번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000061-6161', 2, 1, TO_TIMESTAMP('2024-02-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        1000000, 1000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 61번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000061-6161', 2, 1, TO_TIMESTAMP('2024-02-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        500000, 1500000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 61번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000061-6161', 2, 1, TO_TIMESTAMP('2024-02-01 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        300000, 1200000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 62번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000062-6262', 3, 1, TO_TIMESTAMP('2024-02-01 09:05:00', 'YYYY-MM-DD HH24:MI:SS'),
        1500000, 1500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 62번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000062-6262', 3, 1, TO_TIMESTAMP('2024-02-01 10:05:00', 'YYYY-MM-DD HH24:MI:SS'),
        700000, 2200000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 62번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000062-6262', 3, 1, TO_TIMESTAMP('2024-02-01 11:05:00', 'YYYY-MM-DD HH24:MI:SS'),
        400000, 1800000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 63번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000063-6363', 4, 1, TO_TIMESTAMP('2024-02-01 09:10:00', 'YYYY-MM-DD HH24:MI:SS'),
        2000000, 2000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 63번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000063-6363', 4, 1, TO_TIMESTAMP('2024-02-01 10:10:00', 'YYYY-MM-DD HH24:MI:SS'),
        800000, 2800000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 63번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000063-6363', 4, 1, TO_TIMESTAMP('2024-02-01 11:10:00', 'YYYY-MM-DD HH24:MI:SS'),
        500000, 2300000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 64번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000064-6464', 5, 1, TO_TIMESTAMP('2024-02-01 09:15:00', 'YYYY-MM-DD HH24:MI:SS'),
        500000, 500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 64번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000064-6464', 5, 1, TO_TIMESTAMP('2024-02-01 10:15:00', 'YYYY-MM-DD HH24:MI:SS'),
        200000, 700000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 64번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000064-6464', 5, 1, TO_TIMESTAMP('2024-02-01 11:15:00', 'YYYY-MM-DD HH24:MI:SS'),
        100000, 600000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);


-- 61번 고객 계좌 (001-0000061-6161)의 최종 잔액 1,200,000으로 업데이트
UPDATE Account
SET balance = 1200000
WHERE id = '001-0000061-6161';

-- 62번 고객 계좌 (001-0000062-6262)의 최종 잔액 1,800,000으로 업데이트
UPDATE Account
SET balance = 1800000
WHERE id = '001-0000062-6262';

-- 63번 고객 계좌 (001-0000063-6363)의 최종 잔액 2,300,000으로 업데이트
UPDATE Account
SET balance = 2300000
WHERE id = '001-0000063-6363';

-- 64번 고객 계좌 (001-0000064-6464)의 최종 잔액 600,000으로 업데이트
UPDATE Account
SET balance = 600000
WHERE id = '001-0000064-6464';

-- 7월 1일 이자 내역
INSERT INTO Interest (id, acc_id, registrant_id, branch_id, creation_date, amount, balance, interest_rate,
                      payment_status, version)
VALUES (Interest_seq.nextval, '001-0000073-7373', 1, 1,

        TO_TIMESTAMP('2023-07-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        460, 120000, 4.6, 'N', 1);

-- 8월 1일 이자 내역
INSERT INTO Interest (id, acc_id, registrant_id, branch_id, creation_date, amount, balance, interest_rate,
                      payment_status, version)
VALUES (Interest_seq.nextval, '001-0000073-7373', 1, 1,
        TO_TIMESTAMP('2023-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        460, 120000, 4.6, 'N', 1);


-- 통계용 계좌 A
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000005-6789', 1, 4, 3, 2, TO_TIMESTAMP('2023-03-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, 5000000, 10000000, 'PRIVATE', TO_TIMESTAMP('2023-03-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);


-- A 가입 거래내역
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-6789', 2, 1, TO_TIMESTAMP('2023-03-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0, 300000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);


-- A 현금 입금 5000원 입금 5회
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-6789', 2, 1, TO_TIMESTAMP('2023-03-03 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 305000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-6789', 2, 1, TO_TIMESTAMP('2023-04-03 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 310000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-6789', 2, 1, TO_TIMESTAMP('2023-05-03 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 315000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-6789', 2, 1, TO_TIMESTAMP('2023-06-03 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 320000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-6789', 2, 1, TO_TIMESTAMP('2023-07-03 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 325000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

--  A 계좌 (001-0000005-6789)의 최종 잔액
UPDATE Account
SET balance = 325000
WHERE id = '001-0000005-6789';


-- 통계용 계좌 B
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000006-7890', 1, 5, 3, 2, TO_TIMESTAMP('2023-03-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 100000, 5000000, 10000000, 'PRIVATE', TO_TIMESTAMP('2024-03-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);

-- B 가입 거래내역
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000006-7890', 2, 1, TO_TIMESTAMP('2023-03-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0, 100000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);


-- A계좌 - B계좌 계좌이체 2번
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000064-6464', 2, 1, TO_TIMESTAMP('2023-04-02 00:00:01', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 320000, 'WITHDRAWAL', 'NOR', 'FALSE', '용돈', trade_num_seq.NEXTVAL, '001-0000006-7890');

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000006-7890', 2, 1, TO_TIMESTAMP('2023-04-03 00:00:01', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 105000, 'DEPOSIT', 'NOR', 'FALSE', '용돈', trade_num_seq.NEXTVAL, '001-0000064-6464');

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000064-6464', 2, 1, TO_TIMESTAMP('2023-04-03 00:00:01', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 315000, 'WITHDRAWAL', 'NOR', 'FALSE', '용돈', trade_num_seq.NEXTVAL, '001-0000006-7890');

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000006-7890', 2, 1, TO_TIMESTAMP('2023-04-02 00:00:01', 'YYYY-MM-DD HH24:MI:SS'),
        50000, 110000, 'DEPOSIT', 'NOR', 'FALSE', '용돈', trade_num_seq.NEXTVAL, '001-0000064-6464');


--  A 계좌 (001-0000064-6464)의 최종 잔액
UPDATE Account
SET balance = 315000
WHERE id = '001-0000005-6789';

--  B 계좌 (001-0000006-7890)의 최종 잔액
UPDATE Account
SET balance = 110000
WHERE id = '001-0000006-7890';

-- 통계용 계좌 C
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000007-8991', 1, 17, 6, 2, TO_TIMESTAMP('2024-03-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 100000000, 3000000,3000000 , 'CORPORATION', TO_TIMESTAMP('2024-04-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);

-- C 가입 거래내역
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000007-8991', 2, 1, TO_TIMESTAMP('2023-04-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0, 100000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 통계용 계좌 C
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate,
                     expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status,
                     version)
VALUES ('001-0000007-8992', 1, 17, 6, 2, TO_TIMESTAMP('2024-03-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL,
        '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 50000000, 5000000000,1000000000 , 'CORPORATION', TO_TIMESTAMP('2024-04-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        'OPN', 1);

-- C 가입 거래내역
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status,
                   cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000007-8992', 2, 1, TO_TIMESTAMP('2023-04-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0, 50000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);
