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
INSERT INTO Employee (id, name,birth_date, password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL,  '김철수','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-1111-1111', 'ROLE_MANAGER', 1);

-- 지점 1 행원
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '김하늘','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-5678-9101', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이수진', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-2345-6789', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '박준호', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'test1@gmail.com','010-3456-7890', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정미래','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'test1@gmail.com','010-4567-8901', 'ROLE_EMPLOYEE', 1);

INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '최유리','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-5678-9012', 'ROLE_EMPLOYEE', 1);

-- 지점 2 매니저
INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '박영희','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-2222-2222', 'ROLE_MANAGER', 2);

-- 지점 2 행원
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '임도현','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-6789-0123', 'ROLE_EMPLOYEE', 2);


-- 지점 3 매니저
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이민호', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-3333-3333', 'ROLE_MANAGER', 3);
-- 지점 3 행원
INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '홍서연', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'test1@gmail.com','010-7890-1234', 'ROLE_EMPLOYEE', 3);


-- 지점 4 매니저
INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '최지훈', '1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-4444-4444', 'ROLE_MANAGER', 4);
-- 지점 4 행원
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정세진', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-8901-2345', 'ROLE_EMPLOYEE', 4);


-- 지점 5 매니저
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정유진', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-5555-5555', 'ROLE_MANAGER', 5);

-- 지점 5 행원
INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '민정호', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'test1@gmail.com','010-9012-3456', 'ROLE_EMPLOYEE', 5);

INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이경호', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-0123-4567', 'ROLE_EMPLOYEE', 5);

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
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이준호', '010-1111-2222', 'MALE', '901125-1234567', '서울특별시 강남구 역삼동 123-45', '1990-11-25', '2등급', 2);

-- 2번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김태훈', '010-2222-3333', 'MALE', '850615-2345678', '부산광역시 해운대구 우동 456-78', '1985-06-15', '2등급',  2);

-- 3번 고객
-- 여성
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박지연', '010-3333-4444', 'FEMALE', '920301-3456789', '대구광역시 수성구 범어동 234-56', '1992-03-01', '2등급',  2);

-- 4번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정은주', '010-4444-5555', 'FEMALE', '971215-4567890', '인천광역시 남동구 구월동 567-89', '1997-12-15', '2등급',  2);

-- 5번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '최민아', '010-5555-6666', 'FEMALE', '880110-5678901', '경기도 성남시 분당구 정자동 789-12', '1988-01-10', '2등급',  2);

-- 6번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '김태민', '010-2345-6789', 'MALE', '890123-1234567', '서울특별시 강남구 논현동 123-45', '1989-01-23', '2등급',  8);

-- 7번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '박지윤', '010-3456-7890', 'FEMALE', '760204-2345678', '서울특별시 송파구 가락동 678-90', '1976-02-04', '2등급',  8);

-- 8번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '이수민', '010-4567-8901', 'FEMALE', '950310-3456789', '서울특별시 서초구 반포동 345-67', '1995-03-10', '2등급',  8);

-- 9번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '정하늘', '010-5678-9012', 'MALE', '880521-4567890', '서울특별시 동작구 상도동 890-12', '1988-05-21', '2등급',  8);

-- 10번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '최민호', '010-6789-0123', 'MALE', '720606-5678901', '서울특별시 마포구 상암동 123-45', '1972-06-06', '2등급',  10);

-- 11번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '강영희', '010-7890-1234', 'FEMALE', '990417-6789012', '서울특별시 강북구 수유동 345-67', '1999-04-17', '2등급',  10);

-- 12번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '김하늘', '010-8901-2345', 'FEMALE', '880724-7890123', '서울특별시 중구 명동 456-78', '1988-07-24', '2등급',  10);

-- 13번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '이동훈', '010-9012-3456', 'MALE', '930512-8901234', '서울특별시 영등포구 신길동 567-89', '1993-05-12', '2등급',  10);

-- 14번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '박준호', '010-0123-4567', 'MALE', '860730-9012345', '서울특별시 관악구 봉천동 678-90', '1986-07-30',  '2등급', 12);

-- 15번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '정미래', '010-1234-5678', 'FEMALE', '950812-0123456', '서울특별시 용산구 이태원동 789-12', '1995-08-12',  '2등급', 12);

-- 16번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '이현정', '010-2345-6789', 'FEMALE', '880305-1234567', '서울특별시 서대문구 연희동 890-23', '1988-03-05', '2등급',  12);

---------- 상품 생성 ----------
/*
    지점번호: 순차적
    id 1~7: 보통예금
    id 8~12 : 정기적금
 */

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '청년안심보통예금', 2.5, SYSDATE, '00', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 2, '청년미래보통예금', 2.5, SYSDATE, '00', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 3, '노후든든보통예금', 2.5, SYSDATE, '00', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 4, '노후건강보통예금', 2.5, SYSDATE, '00', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 5, '더편한보통예금', 2.5, SYSDATE, '00', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '기업보통예금', 0.1, SYSDATE, '00', 0.15, 1, 'CORPORATION');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, 'ONE 사업자 통장', 0.1, SYSDATE, '00', 0.15, 1, 'CORPORATION');

--적금 상품 8 - 12번

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '6개월만기정기적금', 2.5, SYSDATE, '06', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '1년만기정기적금', 3, SYSDATE, '12', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '2년만기정기적금', 3.5, SYSDATE, '24', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '3년만기정기적금', 3.5, SYSDATE, '36', 0.15, 1, 'PRIVATE');

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id, account_type)
VALUES (product_seq.NEXTVAL, 1, '1년만기자유적금', 3.5, TO_TIMESTAMP('2024-02-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '12', 0.15, 1, 'PRIVATE');


---------- 계좌 생성 ----------
-- 지점
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status, version)
VALUES ('001-0000001-1234', 1, 1, 1, 2, SYSDATE, 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 250000, 5000000, 10000000, 'PRIVATE', SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status, version)
VALUES ('001-0000002-2345', 1, 2, 2, 2, SYSDATE, 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 200000, 5000000, 10000000, 'PRIVATE',SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status, version)
VALUES ('001-0000003-3456', 1, 3, 3, 2, SYSDATE, 1.0, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 150000, 5000000, 10000000, 'PRIVATE',SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status, version)
VALUES ('001-0000004-4567', 1, 4, 4, 2, SYSDATE, 0.3, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 10000000, 5000000, 10000000, 'PRIVATE',SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status, version)
VALUES ('001-0000005-5678', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 10000000, 5000000, 10000000, 'PRIVATE',SYSDATE, 'OPN', 1);

--거래내역 해지 전용
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type, open_date, status, version)
VALUES ('001-0000006-6789', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, 5000000, 10000000, 'PRIVATE',SYSDATE, 'OPN', 1);

-- 적금계좌 개설 전용(1번 고객 통일)

-- 이미 가입된 적금 (2024/3월가입)
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, account_type, open_date, status, version)
VALUES ('001-0000007-0726', 1, 1, 12, 2, TO_TIMESTAMP('2024-03-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1.0, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1500000, 'PRIVATE', TO_TIMESTAMP('2024-07-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 입출금 보통예금 통장
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type,  open_date, status, version)
VALUES ('001-0000008-7678', 1, 1, 5, 2, TO_TIMESTAMP('2024-04-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 3000000, 5000000, 10000000, 'PRIVATE',  TO_TIMESTAMP('2024-04-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type,  open_date, status, version)
VALUES ('001-0000009-8751', 1, 1, 1, 2, TO_TIMESTAMP('2024-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 5000000, 5000000, 10000000, 'PRIVATE',  TO_TIMESTAMP('2024-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type,  open_date, status, version)
VALUES ('001-0000010-9345', 1, 1, 2, 2, TO_TIMESTAMP('2024-06-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 2000000, 5000000, 10000000, 'PRIVATE',  TO_TIMESTAMP('2024-06-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type,  open_date, status, version)
VALUES ('001-0000011-1056', 1, 1, 3, 2, TO_TIMESTAMP('2024-07-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1.0, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1500000, 5000000, 10000000, 'PRIVATE',  TO_TIMESTAMP('2024-07-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


-- 계좌 가입 내역

-- 1번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 250000, 250000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 2번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000002-2345', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 200000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', trade_num_seq.NEXTVAL);

-- 3번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000003-3456', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150000, 150000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', trade_num_seq.NEXTVAL);

-- 4번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000004-4567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 6000000, 6000000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', trade_num_seq.NEXTVAL);

-- 5번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', trade_num_seq.NEXTVAL);

-- 6번 고객 계좌

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000006-6789', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 700000, 700000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', trade_num_seq.NEXTVAL);
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
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '(주)DataSphere', '02-3456-7890', 'NONE', '000001-000001', '서울특별시 종로구 혜화동 901-34', '1982-06-20', '2등급',  4);

-- 18번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, 'CodeWave Company', '02-4567-8901', 'NONE', '000001-000002', '서울특별시 종로구 무악동 012-45', '1979-08-11', '2등급',  8);

-- 19번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '(주)InnoSoft', '02-4568-8901', 'NONE', '000001-000003', '서울특별시 종로구 가회동 012-46', '1980-08-11',  '2등급', 10);

-- 20번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, security_level, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '(주)TechNova', '02-4569-8901', 'NONE', '000001-000004', '서울특별시 서대문구 연희동 012-46', '1980-08-11', '2등급',  12);


-- [회사 1번 계좌]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, per_trade_limit, daily_limit, account_type,  open_date, status, version)
VALUES ('001-0000017-7734', 1, 17, 5, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 80000000,1000000000,5000000000 , 'CORPORATION', TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

