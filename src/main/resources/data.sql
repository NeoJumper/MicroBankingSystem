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
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이준호', '010-1111-2222', 'MALE', '901125-1234567', '서울특별시 강남구 역삼동 123-45', '1990-11-25', 2);

-- 2번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김태훈', '010-2222-3333', 'MALE', '850615-2345678', '부산광역시 해운대구 우동 456-78', '1985-06-15', 2);

-- 3번 고객
-- 여성
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박지연', '010-3333-4444', 'FEMALE', '920301-3456789', '대구광역시 수성구 범어동 234-56', '1992-03-01', 2);

-- 4번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정은주', '010-4444-5555', 'FEMALE', '971215-4567890', '인천광역시 남동구 구월동 567-89', '1997-12-15', 2);

-- 5번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '최민아', '010-5555-6666', 'FEMALE', '880110-5678901', '경기도 성남시 분당구 정자동 789-12', '1988-01-10', 2);

-- 6번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '김태민', '010-2345-6789', 'MALE', '890123-1234567', '서울특별시 강남구 논현동 123-45', '1989-01-23', 8);

-- 7번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '박지윤', '010-3456-7890', 'FEMALE', '760204-2345678', '서울특별시 송파구 가락동 678-90', '1976-02-04', 8);

-- 8번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '이수민', '010-4567-8901', 'FEMALE', '950310-3456789', '서울특별시 서초구 반포동 345-67', '1995-03-10', 8);

-- 9번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '정하늘', '010-5678-9012', 'MALE', '880521-4567890', '서울특별시 동작구 상도동 890-12', '1988-05-21', 8);

-- 10번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '최민호', '010-6789-0123', 'MALE', '720606-5678901', '서울특별시 마포구 상암동 123-45', '1972-06-06', 10);

-- 11번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '강영희', '010-7890-1234', 'FEMALE', '990417-6789012', '서울특별시 강북구 수유동 345-67', '1999-04-17', 10);

-- 12번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '김하늘', '010-8901-2345', 'FEMALE', '880724-7890123', '서울특별시 중구 명동 456-78', '1988-07-24', 10);

-- 13번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '이동훈', '010-9012-3456', 'MALE', '930512-8901234', '서울특별시 영등포구 신길동 567-89', '1993-05-12', 10);

-- 14번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '박준호', '010-0123-4567', 'MALE', '860730-9012345', '서울특별시 관악구 봉천동 678-90', '1986-07-30', 12);

-- 15번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '정미래', '010-1234-5678', 'FEMALE', '950812-0123456', '서울특별시 용산구 이태원동 789-12', '1995-08-12', 12);

-- 16번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '이현정', '010-2345-6789', 'FEMALE', '880305-1234567', '서울특별시 서대문구 연희동 890-23', '1988-03-05', 12);

---------- 상품 생성 ----------
/*
    지점번호: 순차적
    id 1~5: 보통예금
 */

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id)
VALUES (product_seq.NEXTVAL, 1, '청년안심보통예금', 2.5, SYSDATE, '12개월', 0.15, 1);

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id)
VALUES (product_seq.NEXTVAL, 2, '청년미래보통예금', 2.5, SYSDATE, '12개월', 0.15, 1);

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id)
VALUES (product_seq.NEXTVAL, 3, '노후든든보통예금', 2.5, SYSDATE, '12개월', 0.15, 1);

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id)
VALUES (product_seq.NEXTVAL, 4, '노후건강보통예금', 2.5, SYSDATE, '12개월', 0.15, 1);

INSERT INTO Product (id, branch_id, name, interest_rate, effective_date, period, tax_rate, registrant_id)
VALUES (product_seq.NEXTVAL, 5, '더편한보통예금', 2.5, SYSDATE, '12개월', 0.15, 1);


---------- 계좌 생성 ----------
-- 지점
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000001-1234', 1, 1, 1, 2, SYSDATE, 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 250000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000002-2345', 1, 2, 2, 2, SYSDATE, 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 200000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000003-3456', 1, 3, 3, 2, SYSDATE, 1.0, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 150000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000004-4567', 1, 4, 4, 2, SYSDATE, 0.3, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000005-5678', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

--거래내역 해지 전용
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000006-6789', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

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
VALUES (trade_seq.nextval, '001-0000004-4567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', trade_num_seq.NEXTVAL);

-- 5번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', trade_num_seq.NEXTVAL);

-- 6번 고객 계좌

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000006-6789', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 700000, 700000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', trade_num_seq.NEXTVAL);

-- 계좌 이체

-----------------    1번 고객 계좌 -> 2번 고객 계좌      ----------------------
-- 1번 고객 계좌 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 50000, 200000, 'WITHDRAWAL', 'NOR', 'FALSE', '9월 용돈', trade_num_seq.NEXTVAL, '001-0000002-2345');

-- 2번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000002-2345', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 50000, 250000, 'DEPOSIT', 'NOR', 'FALSE', '9월 용돈', trade_num_seq.CURRVAL, '001-0000001-1234');


-----------------    1번 고객 계좌 -> 3번 고객 계좌      ----------------------


-- 1번 고객 계좌 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 50000, 150000, 'WITHDRAWAL', 'NOR', 'FALSE', '명절 선물', trade_num_seq.NEXTVAL, '001-0000003-3456');

-- 3번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000003-3456', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 50000, 200000, 'DEPOSIT', 'NOR', 'FALSE', '명절 선물', trade_num_seq.CURRVAL, '001-0000001-1234');

----------------     5번 고객 계좌 -> 1번 고객     -------------------------

-- 5번 고객 계좌 출금 --
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000005-5678', 5, 5, TO_TIMESTAMP('2024-08-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 30000, 270000, 'WITHDRAWAL', 'NOR', 'FALSE', '깜짝 선물', trade_num_seq.NEXTVAL, '001-0000001-1234');

-- 1번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 5, 5, TO_TIMESTAMP('2024-08-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 30000, 130000, 'DEPOSIT', 'NOR', 'FALSE', '깜짝 선물', trade_num_seq.CURRVAL, '001-0000005-5678');


-- 계좌 현금 입출금

-- 1번 계좌로 5,000원 현금 입금
INSERT INTO Trade (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.NEXTVAL, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 5000, 135000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

--2번 계좌로 5,000원 현금 입금
INSERT INTO Trade (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.NEXTVAL, '001-0000002-2345',2, 1, TO_TIMESTAMP('2024-08-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 5000, 255000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', trade_num_seq.NEXTVAL);

-- 4번 계좌에서 50,000원 현금 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000004-4567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 50000, 150000, 'WITHDRAWAL', 'NOR', 'TRUE', '생활비 인출', trade_num_seq.NEXTVAL);

-- 5번 계좌에서 50,000원 현금 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 50000, 250000, 'WITHDRAWAL', 'NOR', 'TRUE', '복권 구매', trade_num_seq.NEXTVAL);

-- 계좌 해지

-- 1번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 135000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', trade_num_seq.NEXTVAL);

-- 2번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000002-2345', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 255000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', trade_num_seq.NEXTVAL);

-- 3번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000003-3456', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', trade_num_seq.NEXTVAL);

-- 6번 해지신청 취소 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000006-6789', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', trade_num_seq.NEXTVAL);


-- 계좌 상태(해지) 및 잔액 업데이트
UPDATE Account
SET status = 'CLS',
    balance = 0,
    expire_date = TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS')

WHERE id IN ('001-0000001-1234', '001-0000002-2345', '001-0000003-3456');

UPDATE Account
SET balance = 250000
WHERE id = '001-0000004-4567';

UPDATE Account
SET balance = 220000
WHERE id = '001-0000005-5678';

-- 행원 마감

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 2, 1, 'CLOSED',  10000000, 1210000, 100000, 11110000,trade_num_seq.NEXTVAL);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 3, 1, 'CLOSED', 10000000, 0, 0, 10000000,trade_num_seq.CURRVAL);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 4, 1, 'CLOSED',  10000000, 0, 0, 10000000,trade_num_seq.CURRVAL);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 5, 1, 'CLOSED', 10000000, 0, 0, 10000000,trade_num_seq.CURRVAL);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 6, 1, 'CLOSED',  10000000, 0, 0, 10000000,trade_num_seq.CURRVAL);

-- 거래번호 1: 지점 마감
INSERT INTO BRANCH_CLOSING (closing_date,registrant_id, branch_id,  status, prev_cash_balance, vault_cash, trade_number, version)
VALUES('2024-08-01 00:00:00', 1, 1, 'CLOSED',  50000000,  51110000, trade_num_seq.CURRVAL, 1);


INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number, version)
VALUES('2024-08-02 00:00:00', 2, 1, 'OPEN',  11110000, 0, 590000, null,trade_num_seq.NEXTVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number, version)
VALUES('2024-08-02 00:00:00', 3, 1, 'OPEN', 10000000, 0, 0, 10000000,trade_num_seq.CURRVAL, 1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number,version)
VALUES('2024-08-02 00:00:00', 4, 1, 'CLOSED',  10000000, 0, 0, 10000000,trade_num_seq.CURRVAL,1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number, version)
VALUES('2024-08-02 00:00:00', 5, 1, 'CLOSED', 10000000, 0, 0, 10000000,trade_num_seq.CURRVAL,1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number, version)
VALUES('2024-08-02 00:00:00', 6, 1, 'CLOSED',  10000000, 0, 0, 10000000,trade_num_seq.CURRVAL,1);

-- 거래번호 2: 지점 마감
INSERT INTO BRANCH_CLOSING (closing_date,registrant_id, branch_id,  status, prev_cash_balance, vault_cash, trade_number, version)
VALUES('2024-08-02 00:00:00', 2, 1, 'OPEN',   51110000,  null,trade_num_seq.CURRVAL,1);


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
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '(주)DataSphere', '02-3456-7890', 'NONE', '000001-000001', '서울특별시 종로구 혜화동 901-34', '1982-06-20', 4);

-- 18번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, 'CodeWave Company', '02-4567-8901', 'NONE', '000001-000002', '서울특별시 종로구 무악동 012-45', '1979-08-11', 8);

-- 19번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '(주)InnoSoft', '02-4568-8901', 'NONE', '000001-000003', '서울특별시 종로구 가회동 012-46', '1980-08-11', 10);

-- 20번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '(주)TechNova', '02-4569-8901', 'NONE', '000001-000004', '서울특별시 서대문구 연희동 012-46', '1980-08-11', 12);



---------- 1번 회사 사원 : 1번 지점 개설 등록----------


-- 21번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김민수', '010-6666-7777', 'MALE', '950101-1234567', '서울특별시 강남구 삼성동 101-1', '1995-01-01', 3);

-- 22번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이영희', '010-7777-8888', 'FEMALE', '930202-2345678', '서울특별시 서초구 서초동 202-2', '1993-02-02', 5);

-- 23번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박준형', '010-8888-9999', 'MALE', '900303-3456789', '서울특별시 송파구 잠실동 303-3', '1990-03-03', 2);

-- 24번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '최수진', '010-9999-0000', 'FEMALE', '960404-4567890', '서울특별시 강동구 천호동 404-4', '1996-04-04', 4);

-- 25번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '홍길동', '010-0000-1111', 'MALE', '880505-5678901', '서울특별시 노원구 상계동 505-5', '1988-05-05', 6);

-- 26번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '신지훈', '010-1010-1111', 'MALE', '920606-6789012', '서울특별시 중랑구 면목동 606-6', '1992-06-06', 4);

-- 27번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '오수민', '010-1212-1313', 'FEMALE', '970707-7890123', '서울특별시 성북구 길음동 707-7', '1997-07-07', 5);

-- 28번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '강민호', '010-1414-1515', 'MALE', '850808-8901234', '서울특별시 은평구 불광동 808-8', '1985-08-08', 2);

-- 29번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '윤서연', '010-1616-1717', 'FEMALE', '940909-9012345', '서울특별시 마포구 합정동 909-9', '1994-09-09', 6);

-- 30번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정현우', '010-1818-1919', 'MALE', '910101-0123456', '서울특별시 동대문구 이문동 101-1', '1991-01-01', 3);



---------- 2번 회사 사원 : 2번 지점 등록 ----------



-- 31번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '김지훈', '010-2121-2222', 'MALE', '970808-1234567', '서울특별시 강남구 도곡동 101-1', '1997-08-08', 8);

-- 32번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '박수민', '010-2323-2424', 'FEMALE', '850909-2345678', '서울특별시 서초구 방배동 202-2', '1985-09-09', 8);

-- 33번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '이현우', '010-2525-2626', 'MALE', '900101-3456789', '서울특별시 송파구 문정동 303-3', '1990-01-01', 8);

-- 34번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '정민서', '010-2727-2828', 'FEMALE', '940202-4567890', '서울특별시 강동구 둔촌동 404-4', '1994-02-02', 8);

-- 35번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '최민준', '010-2929-3030', 'MALE', '920303-5678901', '서울특별시 노원구 중계동 505-5', '1992-03-03', 8);

-- 36번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '김서연', '010-3131-3232', 'FEMALE', '870404-6789012', '서울특별시 중랑구 상봉동 606-6', '1987-04-04', 8);

-- 37번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '장동현', '010-3333-3434', 'MALE', '890505-7890123', '서울특별시 성북구 종암동 707-7', '1989-05-05', 8);

-- 38번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '한지민', '010-3535-3636', 'FEMALE', '950606-8901234', '서울특별시 은평구 응암동 808-8', '1995-06-06', 8);

-- 39번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '서준혁', '010-3737-3838', 'MALE', '930707-9012345', '서울특별시 마포구 서교동 909-9', '1993-07-07', 8);

-- 40번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 2, '신예은', '010-3939-4040', 'FEMALE', '910808-0123456', '서울특별시 동대문구 회기동 101-1', '1991-08-08', 8);



---------- 3번 회사 사원 : 3번 지점 등록 ----------



-- 41번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '김하늘', '010-4141-4242', 'FEMALE', '910101-1234567', '서울특별시 강남구 역삼동 101-1', '1991-01-01', 10);

-- 42번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '박준호', '010-4343-4444', 'MALE', '920202-2345678', '서울특별시 서초구 반포동 202-2', '1992-02-02', 10);

-- 43번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '이수민', '010-4545-4646', 'FEMALE', '930303-3456789', '서울특별시 송파구 가락동 303-3', '1993-03-03', 10);

-- 44번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '정민준', '010-4747-4848', 'MALE', '940404-4567890', '서울특별시 강동구 명일동 404-4', '1994-04-04', 10);

-- 45번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '최서연', '010-4949-5050', 'FEMALE', '950505-5678901', '서울특별시 노원구 공릉동 505-5', '1995-05-05', 10);

-- 46번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '김현우', '010-5151-5252', 'MALE', '960606-6789012', '서울특별시 중랑구 묵동 606-6', '1996-06-06', 10);

-- 47번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '장예은', '010-5353-5454', 'FEMALE', '970707-7890123', '서울특별시 성북구 석관동 707-7', '1997-07-07', 10);

-- 48번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '한지훈', '010-5555-5656', 'MALE', '980808-8901234', '서울특별시 은평구 신사동 808-8', '1998-08-08', 10);

-- 49번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '서민지', '010-5757-5858', 'FEMALE', '990909-9012345', '서울특별시 마포구 연남동 909-9', '1999-09-09', 10);

-- 50번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 3, '신동현', '010-5959-6060', 'MALE', '000101-0123456', '서울특별시 동대문구 청량리동 101-1', '2000-01-01', 10);



---------- 4번 회사 사원 : 4번 지점 등록 ----------




-- 51번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '김예준', '010-6161-6262', 'MALE', '910101-1234567', '서울특별시 강남구 청담동 101-1', '1991-01-01', 12);

-- 52번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '이서연', '010-6363-6464', 'FEMALE', '920202-2345678', '서울특별시 서초구 잠원동 202-2', '1992-02-02', 12);

-- 53번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '박민준', '010-6565-6666', 'MALE', '930303-3456789', '서울특별시 송파구 방이동 303-3', '1993-03-03', 12);

-- 54번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '최지민', '010-6767-6868', 'FEMALE', '940404-4567890', '서울특별시 강동구 상일동 404-4', '1994-04-04', 12);

-- 55번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '정현우', '010-6969-7070', 'MALE', '950505-5678901', '서울특별시 노원구 월계동 505-5', '1995-05-05', 12);

-- 56번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '김수민', '010-7171-7272', 'FEMALE', '960606-6789012', '서울특별시 중랑구 면목동 606-6', '1996-06-06', 12);

-- 57번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '이준호', '010-7373-7474', 'MALE', '970707-7890123', '서울특별시 성북구 장위동 707-7', '1997-07-07', 12);

-- 58번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '박서현', '010-7575-7676', 'FEMALE', '980808-8901234', '서울특별시 은평구 역촌동 808-8', '1998-08-08', 12);

-- 59번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '최민수', '010-7777-7878', 'MALE', '990909-9012345', '서울특별시 마포구 도화동 909-9', '1999-09-09', 12);

-- 60번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 4, '정예은', '010-7979-8080', 'FEMALE', '000101-0123456', '서울특별시 동대문구 답십리동 101-1', '2000-01-01', 12);


/*
    [계좌 생성]
        - 계좌 개설은 고객이 등록된 지점에서 가입 내역이 있도록 작성했음

    17번 고객: 회사 1번
    21~30번 고객: 회사 1번 사원
    => 지점 1에서 가입

    18번 고객: 회사 2번
    31~40번 고객: 회사 2번 사원
    => 지점 2에서 가입

    19번 고객: 회사 3번
    31~40번 고객: 회사 3번 사원
    => 지점 3에서 가입

    20번 고객: 회사 4번
    31~40번 고객: 회사 4번 사원
    => 지점 4에서 가입

 */

-- [회사 1번 계좌]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000017-7734', 1, 17, 5, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 80000000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


-- [회사 2번 계좌]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000018-7834', 2, 18, 5, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 80000000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


-- [회사 3번 계좌]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000019-1834', 3, 19, 5, 10, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 80000000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


-- [회사 4번 계좌]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000020-4834', 4, 20, 5, 12, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 80000000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


--------------------


-- 계좌 생성 : 고객 21번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000021-2121', 1, 21, 3, 3, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 500000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 22번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000022-2222', 1, 22, 2, 5, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.3, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 750000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 23번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000023-2323', 1, 23, 4, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1200000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 24번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000024-2424', 1, 24, 1, 4, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.2, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 25번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000025-2525', 1, 25, 5, 6, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.8, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 2000000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 26번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000026-2626', 1, 26, 2, 3, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.4, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 850000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 27번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000027-2727', 1, 27, 1, 5, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.1, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 400000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 28번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000028-2828', 1, 28, 3, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 950000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 29번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000029-2929', 1, 29, 4, 4, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1300000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 30번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000030-3030', 1, 30, 5, 6, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 2500000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);




-----  계좌 생성 : 고객 18번 : 회사 2번 -----



-- 계좌 생성 : 고객 31번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000031-3131', 2, 31, 2, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.4, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1500000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 32번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000032-3232', 2, 32, 1, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1200000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 33번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000033-3333', 2, 33, 3, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 800000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 34번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000034-3434', 2, 34, 4, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1700000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 35번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000035-3535', 2, 35, 2, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.3, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 900000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 36번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000036-3636', 2, 36, 1, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1400000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 37번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000037-3737', 2, 37, 3, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.2, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1100000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 38번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000038-3838', 2, 38, 4, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 600000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 39번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000039-3939', 2, 39, 5, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.8, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 950000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 40번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('002-0000040-4040', 2, 40, 1, 8, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 700000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);





-----  계좌 생성 : 고객 19번 : 회사 3번 -----





-- 계좌 생성 : 고객 41번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000041-4141', 3, 41, 1, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1500000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 42번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000042-4242', 3, 42, 2, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.3, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1000000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 43번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000043-4343', 3, 43, 4, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 850000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 44번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000044-4444', 3, 44, 3, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1400000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 45번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000045-4545', 3, 45, 2, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.2, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 900000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 46번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000046-4646', 3, 46, 5, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1800000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 47번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000047-4747', 3, 47, 3, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1600000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 48번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000048-4848', 3, 48, 4, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.4, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1200000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 49번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000049-4949', 3, 49, 1, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.8, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 700000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 50번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('003-0000050-5050', 3, 50, 5, 10, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 900000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);





-----  계좌 생성 : 고객 20번 : 회사 4번 -----





-- 계좌 생성 : 고객 51번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000051-5151', 4, 51, 2, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.4, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1200000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 52번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000052-5252', 4, 52, 1, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1500000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 53번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000053-5353', 4, 53, 3, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 900000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 54번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000054-5454', 4, 54, 4, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.3, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1300000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 55번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000055-5555', 4, 55, 5, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1800000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 56번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000056-5656', 4, 56, 2, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.2, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1100000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 57번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000057-5757', 4, 57, 1, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 900000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 58번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000058-5858', 4, 58, 3, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.8, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1400000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 59번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000059-5959', 4, 59, 5, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.4, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 750000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 계좌 생성 : 고객 60번
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('004-0000060-6060', 4, 60, 4, 12, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1250000, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

/*
    [거래 내역: 계좌 생성]
    - 각 계좌 '계좌개설' 내역 생성

 */

-- 21번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000021-2121', 3, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 500000, 500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 22번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000022-2222', 5, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 750000, 750000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 23번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000023-2323', 2, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1200000, 1200000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 24번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000024-2424', 4, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 25번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000025-2525', 6, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2000000, 2000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 26번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000026-2626', 3, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 850000, 850000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 27번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000027-2727', 5, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 400000, 400000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 28번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000028-2828', 2, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 950000, 950000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 29번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000029-2929', 4, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1300000, 1300000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 30번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000030-3030', 6, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 2500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 31번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000031-3131', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1500000, 1500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 32번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000032-3232', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1200000, 1200000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 33번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000033-3333', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 800000, 800000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 34번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000034-3434', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1700000, 1700000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 35번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000035-3535', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 900000, 900000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 36번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000036-3636', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1400000, 1400000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 37번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000037-3737', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1100000, 1100000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 38번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000038-3838', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 600000, 600000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 39번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000039-3939', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 950000, 950000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 40번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '002-0000040-4040', 8, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 700000, 700000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 41번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000041-4141', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1500000, 1500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 42번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000042-4242', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1000000, 1000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 43번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000043-4343', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 850000, 850000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 44번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000044-4444', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1400000, 1400000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 45번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000045-4545', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 900000, 900000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 46번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000046-4646', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1800000, 1800000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 47번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000047-4747', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1600000, 1600000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 48번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000048-4848', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1200000, 1200000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 49번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000049-4949', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 700000, 700000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 50번 고객 계좌 등록
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '003-0000050-5050', 10, 3, TO_TIMESTAMP('2024-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 900000, 900000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

/*
    [거래 내역: 계좌 생성]
    고객 17번 = 회사 1번 계좌에서 사원들에게 월급
    21~30번 고객 계좌로 이체

 */

-- 17번 계좌 -> 21번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 77500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000021-2121');

-- 21번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000021-2121', 3, 1, TO_TIMESTAMP('2024-02-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3000000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 22번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 75000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000022-2222');

-- 22번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000022-2222', 5, 1, TO_TIMESTAMP('2024-02-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3250000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 23번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 72500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000023-2323');

-- 23번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000023-2323', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3700000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 24번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 70000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000024-2424');

-- 24번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000024-2424', 4, 1, TO_TIMESTAMP('2024-02-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 2800000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 25번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 67500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000025-2525');

-- 25번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000025-2525', 6, 1, TO_TIMESTAMP('2024-02-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 4500000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 26번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 65000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000026-2626');

-- 26번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000026-2626', 3, 1, TO_TIMESTAMP('2024-02-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3350000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 27번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 62500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000027-2727');

-- 27번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000027-2727', 5, 1, TO_TIMESTAMP('2024-02-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 2900000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 28번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 60000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000028-2828');

-- 28번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000028-2828', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3450000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 29번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:08', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 57500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000029-2929');

-- 29번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000029-2929', 4, 1, TO_TIMESTAMP('2024-02-01 00:00:08', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3800000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

-- 17번 계좌 -> 30번 고객 월급 지급
-- 17번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000017-7734', 2, 1, TO_TIMESTAMP('2024-02-01 00:00:09', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 55000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '001-0000030-3030');

-- 30번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000030-3030', 6, 1, TO_TIMESTAMP('2024-02-01 00:00:09', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 5000000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000017-7734');

----- 계좌 잔액 업데이트 -----
-- 회사 1번 계좌 (001-0000017-7734)의 최종 잔액 55,000,000으로 업데이트
UPDATE Account
SET balance = 55000000
WHERE id = '001-0000017-7734';

-- 21번 고객 계좌 (001-0000021-2121)의 최종 잔액 5,500,000으로 업데이트
UPDATE Account
SET balance = 5500000
WHERE id = '001-0000021-2121';

-- 22번 고객 계좌 (001-0000022-2222)의 최종 잔액 5,750,000으로 업데이트
UPDATE Account
SET balance = 5750000
WHERE id = '001-0000022-2222';

-- 23번 고객 계좌 (001-0000023-2323)의 최종 잔액 3,700,000으로 업데이트
UPDATE Account
SET balance = 3700000
WHERE id = '001-0000023-2323';

-- 24번 고객 계좌 (001-0000024-2424)의 최종 잔액 2,800,000으로 업데이트
UPDATE Account
SET balance = 2800000
WHERE id = '001-0000024-2424';

-- 25번 고객 계좌 (001-0000025-2525)의 최종 잔액 4,500,000으로 업데이트
UPDATE Account
SET balance = 4500000
WHERE id = '001-0000025-2525';

-- 26번 고객 계좌 (001-0000026-2626)의 최종 잔액 3,350,000으로 업데이트
UPDATE Account
SET balance = 3350000
WHERE id = '001-0000026-2626';

-- 27번 고객 계좌 (001-0000027-2727)의 최종 잔액 2,900,000으로 업데이트
UPDATE Account
SET balance = 2900000
WHERE id = '001-0000027-2727';

-- 28번 고객 계좌 (001-0000028-2828)의 최종 잔액 3,450,000으로 업데이트
UPDATE Account
SET balance = 3450000
WHERE id = '001-0000028-2828';

-- 29번 고객 계좌 (001-0000029-2929)의 최종 잔액 3,800,000으로 업데이트
UPDATE Account
SET balance = 3800000
WHERE id = '001-0000029-2929';

-- 30번 고객 계좌 (001-0000030-3030)의 최종 잔액 5,000,000으로 업데이트
UPDATE Account
SET balance = 5000000
WHERE id = '001-0000030-3030';


/*
    18번 고객 = 회사 2번 계좌에서 월급 지급
    31~40번 고객

 */

-- 18번 계좌 -> 31번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 77500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000031-3131');

-- 31번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000031-3131', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 4000000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 32번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 75000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000032-3232');

-- 32번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000032-3232', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3700000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 33번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 72500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000033-3333');

-- 33번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000033-3333', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3300000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 34번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 70000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000034-3434');

-- 34번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000034-3434', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 4200000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 35번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 67500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000035-3535');

-- 35번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000035-3535', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3400000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 36번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 65000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000036-3636');

-- 36번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000036-3636', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3900000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 37번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 62500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000037-3737');

-- 37번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000037-3737', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3600000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 38번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 60000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000038-3838');

-- 38번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000038-3838', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3100000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 39번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:08', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 57500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000039-3939');

-- 39번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000039-3939', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:08', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3450000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');

-- 18번 계좌 -> 40번 고객 월급 지급
-- 18번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000018-7834', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:09', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 55000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '002-0000040-4040');

-- 40번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '002-0000040-4040', 8, 2, TO_TIMESTAMP('2024-02-01 00:00:09', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3200000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000018-7834');


----- 잔액 업데이트 -----

-- 회사 2번 계좌 (001-0000018-7834)의 최종 잔액 55,000,000으로 업데이트
UPDATE Account
SET balance = 55000000
WHERE id = '001-0000018-7834';

-- 31번 고객 계좌 (002-0000031-3131)의 최종 잔액 4,000,000으로 업데이트
UPDATE Account
SET balance = 4000000
WHERE id = '002-0000031-3131';

-- 32번 고객 계좌 (002-0000032-3232)의 최종 잔액 3,700,000으로 업데이트
UPDATE Account
SET balance = 3700000
WHERE id = '002-0000032-3232';

-- 33번 고객 계좌 (002-0000033-3333)의 최종 잔액 3,300,000으로 업데이트
UPDATE Account
SET balance = 3300000
WHERE id = '002-0000033-3333';

-- 34번 고객 계좌 (002-0000034-3434)의 최종 잔액 4,200,000으로 업데이트
UPDATE Account
SET balance = 4200000
WHERE id = '002-0000034-3434';

-- 35번 고객 계좌 (002-0000035-3535)의 최종 잔액 3,400,000으로 업데이트
UPDATE Account
SET balance = 3400000
WHERE id = '002-0000035-3535';

-- 36번 고객 계좌 (002-0000036-3636)의 최종 잔액 3,900,000으로 업데이트
UPDATE Account
SET balance = 3900000
WHERE id = '002-0000036-3636';

-- 37번 고객 계좌 (002-0000037-3737)의 최종 잔액 3,600,000으로 업데이트
UPDATE Account
SET balance = 3600000
WHERE id = '002-0000037-3737';

-- 38번 고객 계좌 (002-0000038-3838)의 최종 잔액 3,100,000으로 업데이트
UPDATE Account
SET balance = 3100000
WHERE id = '002-0000038-3838';

-- 39번 고객 계좌 (002-0000039-3939)의 최종 잔액 3,450,000으로 업데이트
UPDATE Account
SET balance = 3450000
WHERE id = '002-0000039-3939';

-- 40번 고객 계좌 (002-0000040-4040)의 최종 잔액 3,200,000으로 업데이트
UPDATE Account
SET balance = 3200000
WHERE id = '002-0000040-4040';

/*
    계좌 19번 = 회사 3번 계좌에서 월급 지급
    41~50번 고객 계좌로 입금

 */

-- 19번 계좌 -> 41번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 77500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000041-4141');

-- 41번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000041-4141', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 4000000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 42번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 75000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000042-4242');

-- 42번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000042-4242', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3500000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 43번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 72500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000043-4343');

-- 43번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000043-4343', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3350000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 44번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 70000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000044-4444');

-- 44번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000044-4444', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3900000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 45번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 67500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000045-4545');

-- 45번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000045-4545', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3400000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 46번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 65000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000046-4646');

-- 46번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000046-4646', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 4300000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 47번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 62500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000047-4747');

-- 47번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000047-4747', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 4100000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 48번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 60000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000048-4848');

-- 48번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000048-4848', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3700000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 49번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:08', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 57500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000049-4949');

-- 49번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000049-4949', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:08', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3200000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

-- 19번 계좌 -> 50번 고객 월급 지급
-- 19번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000019-1834', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:09', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 55000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '003-0000050-5050');

-- 50번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '003-0000050-5050', 10, 3, TO_TIMESTAMP('2024-03-01 00:00:09', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3750000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000019-1834');

----- 잔액 업데이트 -----

-- 회사 3번 계좌 (001-0000019-1834)의 최종 잔액 55,000,000으로 업데이트
UPDATE Account
SET balance = 55000000
WHERE id = '001-0000019-1834';

-- 41번 고객 계좌 (003-0000041-4141)의 최종 잔액 4,000,000으로 업데이트
UPDATE Account
SET balance = 4000000
WHERE id = '003-0000041-4141';

-- 42번 고객 계좌 (003-0000042-4242)의 최종 잔액 3,500,000으로 업데이트
UPDATE Account
SET balance = 3500000
WHERE id = '003-0000042-4242';

-- 43번 고객 계좌 (003-0000043-4343)의 최종 잔액 3,350,000으로 업데이트
UPDATE Account
SET balance = 3350000
WHERE id = '003-0000043-4343';

-- 44번 고객 계좌 (003-0000044-4444)의 최종 잔액 3,900,000으로 업데이트
UPDATE Account
SET balance = 3900000
WHERE id = '003-0000044-4444';

-- 45번 고객 계좌 (003-0000045-4545)의 최종 잔액 3,400,000으로 업데이트
UPDATE Account
SET balance = 3400000
WHERE id = '003-0000045-4545';

-- 46번 고객 계좌 (003-0000046-4646)의 최종 잔액 4,300,000으로 업데이트
UPDATE Account
SET balance = 4300000
WHERE id = '003-0000046-4646';

-- 47번 고객 계좌 (003-0000047-4747)의 최종 잔액 4,100,000으로 업데이트
UPDATE Account
SET balance = 4100000
WHERE id = '003-0000047-4747';

-- 48번 고객 계좌 (003-0000048-4848)의 최종 잔액 3,700,000으로 업데이트
UPDATE Account
SET balance = 3700000
WHERE id = '003-0000048-4848';

-- 49번 고객 계좌 (003-0000049-4949)의 최종 잔액 3,200,000으로 업데이트
UPDATE Account
SET balance = 3200000
WHERE id = '003-0000049-4949';

-- 50번 고객 계좌 (003-0000050-5050)의 최종 잔액 3,750,000으로 업데이트
UPDATE Account
SET balance = 3750000
WHERE id = '003-0000050-5050';

/*
    20번 계좌 = 회사 4번 계좌에서 월급 지급
    51~60번 고객 계좌로 지급

 */

-- 20번 계좌 -> 51번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 77500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000051-5151');

-- 51번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000051-5151', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3700000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 52번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 75000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000052-5252');

-- 52번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000052-5252', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 4000000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 53번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 72500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000053-5353');

-- 53번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000053-5353', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 1150000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 54번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 70000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000054-5454');

-- 54번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000054-5454', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 1550000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 55번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 67500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000055-5555');

-- 55번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000055-5555', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 2050000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 56번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 65000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000056-5656');

-- 56번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000056-5656', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3600000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 57번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 62500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000057-5757');

-- 57번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000057-5757', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 4250000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 58번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 60000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000058-5858');

-- 58번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000058-5858', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3900000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 59번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:08', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 57500000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000059-5959');

-- 59번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000059-5959', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:08', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 1025000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

-- 20번 계좌 -> 60번 고객 월급 지급
-- 20번 고객 계좌 출금 (월급 지급)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000020-4834', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:09', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 55000000, 'WITHDRAWAL', 'NOR', 'FALSE', '월급 지급', trade_num_seq.NEXTVAL, '004-0000060-6060');

-- 60번 고객 계좌 입금 (월급 수령)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '004-0000060-6060', 12, 4, TO_TIMESTAMP('2024-04-01 00:00:09', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 3750000, 'DEPOSIT', 'NOR', 'FALSE', '월급 지급', trade_num_seq.CURRVAL, '001-0000020-4834');

----- 잔액 업데이트 -----
-- 회사 4번 계좌 (001-0000020-4834)의 최종 잔액 55,000,000으로 업데이트
UPDATE Account
SET balance = 55000000
WHERE id = '001-0000020-4834';

-- 51번 고객 계좌 (004-0000051-5151)의 최종 잔액 3,700,000으로 업데이트
UPDATE Account
SET balance = 3700000
WHERE id = '004-0000051-5151';

-- 52번 고객 계좌 (004-0000052-5252)의 최종 잔액 4,000,000으로 업데이트
UPDATE Account
SET balance = 4000000
WHERE id = '004-0000052-5252';

-- 53번 고객 계좌 (004-0000053-5353)의 최종 잔액 1,150,000으로 업데이트
UPDATE Account
SET balance = 1150000
WHERE id = '004-0000053-5353';

-- 54번 고객 계좌 (004-0000054-5454)의 최종 잔액 1,550,000으로 업데이트
UPDATE Account
SET balance = 1550000
WHERE id = '004-0000054-5454';

-- 55번 고객 계좌 (004-0000055-5555)의 최종 잔액 2,050,000으로 업데이트
UPDATE Account
SET balance = 2050000
WHERE id = '004-0000055-5555';

-- 56번 고객 계좌 (004-0000056-5656)의 최종 잔액 3,600,000으로 업데이트
UPDATE Account
SET balance = 3600000
WHERE id = '004-0000056-5656';

-- 57번 고객 계좌 (004-0000057-5757)의 최종 잔액 4,250,000으로 업데이트
UPDATE Account
SET balance = 4250000
WHERE id = '004-0000057-5757';

-- 58번 고객 계좌 (004-0000058-5858)의 최종 잔액 3,900,000으로 업데이트
UPDATE Account
SET balance = 3900000
WHERE id = '004-0000058-5858';

-- 59번 고객 계좌 (004-0000059-5959)의 최종 잔액 1,025,000으로 업데이트
UPDATE Account
SET balance = 1025000
WHERE id = '004-0000059-5959';

-- 60번 고객 계좌 (004-0000060-6060)의 최종 잔액 3,750,000으로 업데이트
UPDATE Account
SET balance = 3750000
WHERE id = '004-0000060-6060';

-- 추후 등록일 / 등록자 / 수정일 / 수정자 각 INSERT 와 UPDATE 문에 맞춰서 작성 예정



-- 통계용 더미

-- 61번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김태희', '010-8080-9090', 'FEMALE', '881010-1234567', '서울특별시 강남구 삼성동 101-1', '1988-10-10', 2);

-- 62번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이민호', '010-8181-9191', 'MALE', '891011-2345678', '서울특별시 강남구 역삼동 202-2', '1989-10-11', 3);

-- 63번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박보검', '010-8282-9292', 'MALE', '901012-3456789', '서울특별시 강남구 논현동 303-3', '1990-10-12', 4);

-- 64번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '최지우', '010-8383-9393', 'FEMALE', '911013-4567890', '서울특별시 강남구 선릉동 404-4', '1991-10-13', 5);

-- 65번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정해인', '010-8484-9494', 'MALE', '921014-5678901', '서울특별시 강남구 청담동 505-5', '1992-10-14', 6);

-- 66번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '송중기', '010-8585-9595', 'MALE', '931015-6789012', '서울특별시 강남구 신사동 606-6', '1993-10-15', 2);

-- 67번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '전지현', '010-8686-9696', 'FEMALE', '941016-7890123', '서울특별시 강남구 압구정동 707-7', '1994-10-16', 3);

-- 68번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '강동원', '010-8787-9797', 'MALE', '951017-8901234', '서울특별시 강남구 역삼동 808-8', '1995-10-17', 4);

-- 69번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '손예진', '010-8888-9898', 'FEMALE', '961018-9012345', '서울특별시 강남구 삼성동 909-9', '1996-10-18', 5);

-- 70번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이준기', '010-8989-9999', 'MALE', '971019-0123456', '서울특별시 강남구 논현동 1010-10', '1997-10-19', 6);


-- [계좌 생성: 고객 61번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000061-6161', 1, 61, 3, 2, TO_TIMESTAMP('2024-02-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1000000, TO_TIMESTAMP('2024-02-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 62번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000062-6262', 1, 62, 2, 3, TO_TIMESTAMP('2024-02-01 09:05:00', 'YYYY-MM-DD HH24:MI:SS'), 0.3, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1500000, TO_TIMESTAMP('2024-02-01 09:05:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 63번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000063-6363', 1, 63, 4, 4, TO_TIMESTAMP('2024-02-01 09:10:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 2000000, TO_TIMESTAMP('2024-02-01 09:10:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 64번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000064-6464', 1, 64, 1, 5, TO_TIMESTAMP('2024-02-01 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 0.2, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 500000, TO_TIMESTAMP('2024-02-01 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 65번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000065-6565', 1, 65, 5, 6, TO_TIMESTAMP('2024-02-01 09:20:00', 'YYYY-MM-DD HH24:MI:SS'), 0.8, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 2500000, TO_TIMESTAMP('2024-02-01 09:20:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 66번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000066-6666', 1, 66, 2, 2, TO_TIMESTAMP('2024-02-01 09:25:00', 'YYYY-MM-DD HH24:MI:SS'), 0.4, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1800000, TO_TIMESTAMP('2024-02-01 09:25:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 67번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000067-6767', 1, 67, 1, 5, TO_TIMESTAMP('2024-02-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0.1, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, TO_TIMESTAMP('2024-02-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 68번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000068-6868', 1, 68, 3, 4, TO_TIMESTAMP('2024-02-01 09:35:00', 'YYYY-MM-DD HH24:MI:SS'), 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1200000, TO_TIMESTAMP('2024-02-01 09:35:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 69번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000069-6969', 1, 69, 4, 5, TO_TIMESTAMP('2024-02-01 09:40:00', 'YYYY-MM-DD HH24:MI:SS'), 0.6, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 1700000, TO_TIMESTAMP('2024-02-01 09:40:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- [계좌 생성: 고객 70번]
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('001-0000070-7070', 1, 70, 5, 6, TO_TIMESTAMP('2024-02-01 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 2200000, TO_TIMESTAMP('2024-02-01 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

-- 61번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000061-6161', 2, 1, TO_TIMESTAMP('2024-02-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1000000, 1000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 61번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000061-6161', 2, 1, TO_TIMESTAMP('2024-02-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 500000, 1500000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 61번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000061-6161', 2, 1, TO_TIMESTAMP('2024-02-01 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 1200000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 62번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000062-6262', 3, 1, TO_TIMESTAMP('2024-02-01 09:05:00', 'YYYY-MM-DD HH24:MI:SS'), 1500000, 1500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 62번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000062-6262', 3, 1, TO_TIMESTAMP('2024-02-01 10:05:00', 'YYYY-MM-DD HH24:MI:SS'), 700000, 2200000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 62번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000062-6262', 3, 1, TO_TIMESTAMP('2024-02-01 11:05:00', 'YYYY-MM-DD HH24:MI:SS'), 400000, 1800000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 63번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000063-6363', 4, 1, TO_TIMESTAMP('2024-02-01 09:10:00', 'YYYY-MM-DD HH24:MI:SS'), 2000000, 2000000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 63번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000063-6363', 4, 1, TO_TIMESTAMP('2024-02-01 10:10:00', 'YYYY-MM-DD HH24:MI:SS'), 800000, 2800000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 63번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000063-6363', 4, 1, TO_TIMESTAMP('2024-02-01 11:10:00', 'YYYY-MM-DD HH24:MI:SS'), 500000, 2300000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 64번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000064-6464', 5, 1, TO_TIMESTAMP('2024-02-01 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 500000, 500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 64번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000064-6464', 5, 1, TO_TIMESTAMP('2024-02-01 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 700000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 64번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000064-6464', 5, 1, TO_TIMESTAMP('2024-02-01 11:15:00', 'YYYY-MM-DD HH24:MI:SS'), 100000, 600000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 65번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000065-6565', 6, 1, TO_TIMESTAMP('2024-02-01 09:20:00', 'YYYY-MM-DD HH24:MI:SS'), 2500000, 2500000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 65번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000065-6565', 6, 1, TO_TIMESTAMP('2024-02-01 10:20:00', 'YYYY-MM-DD HH24:MI:SS'), 1000000, 3500000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 65번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000065-6565', 6, 1, TO_TIMESTAMP('2024-02-01 11:20:00', 'YYYY-MM-DD HH24:MI:SS'), 500000, 3000000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 66번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000066-6666', 2, 1, TO_TIMESTAMP('2024-02-01 09:25:00', 'YYYY-MM-DD HH24:MI:SS'), 1800000, 1800000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 66번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000066-6666', 2, 1, TO_TIMESTAMP('2024-02-01 10:25:00', 'YYYY-MM-DD HH24:MI:SS'), 400000, 2200000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 66번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000066-6666', 2, 1, TO_TIMESTAMP('2024-02-01 11:25:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 2000000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 67번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000067-6767', 5, 1, TO_TIMESTAMP('2024-02-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 67번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000067-6767', 5, 1, TO_TIMESTAMP('2024-02-01 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 150000, 450000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 67번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000067-6767', 5, 1, TO_TIMESTAMP('2024-02-01 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 50000, 400000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 68번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000068-6868', 4, 1, TO_TIMESTAMP('2024-02-01 09:35:00', 'YYYY-MM-DD HH24:MI:SS'), 1200000, 1200000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 68번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000068-6868', 4, 1, TO_TIMESTAMP('2024-02-01 10:35:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 1500000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 68번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000068-6868', 4, 1, TO_TIMESTAMP('2024-02-01 11:35:00', 'YYYY-MM-DD HH24:MI:SS'), 500000, 1000000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 69번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000069-6969', 5, 1, TO_TIMESTAMP('2024-02-01 09:40:00', 'YYYY-MM-DD HH24:MI:SS'), 1700000, 1700000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 69번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000069-6969', 5, 1, TO_TIMESTAMP('2024-02-01 10:40:00', 'YYYY-MM-DD HH24:MI:SS'), 800000, 2500000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 69번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000069-6969', 5, 1, TO_TIMESTAMP('2024-02-01 11:40:00', 'YYYY-MM-DD HH24:MI:SS'), 700000, 1800000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

-- 70번 고객 계좌 등록 (OPEN)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000070-7070', 6, 1, TO_TIMESTAMP('2024-02-01 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 2200000, 2200000, 'OPEN', 'NOR', 'TRUE', '계좌개설', trade_num_seq.NEXTVAL);

-- 70번 고객 계좌 입금 (DEPOSIT)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000070-7070', 6, 1, TO_TIMESTAMP('2024-02-01 10:45:00', 'YYYY-MM-DD HH24:MI:SS'), 1000000, 3200000, 'DEPOSIT', 'NOR', 'TRUE', '현금 입금', trade_num_seq.NEXTVAL);

-- 70번 고객 계좌 출금 (WITHDRAWAL)
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000070-7070', 6, 1, TO_TIMESTAMP('2024-02-01 11:45:00', 'YYYY-MM-DD HH24:MI:SS'), 500000, 2700000, 'WITHDRAWAL', 'NOR', 'TRUE', '현금 출금', trade_num_seq.NEXTVAL);

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

-- 65번 고객 계좌 (001-0000065-6565)의 최종 잔액 3,000,000으로 업데이트
UPDATE Account
SET balance = 3000000
WHERE id = '001-0000065-6565';

-- 66번 고객 계좌 (001-0000066-6666)의 최종 잔액 2,000,000으로 업데이트
UPDATE Account
SET balance = 2000000
WHERE id = '001-0000066-6666';

-- 67번 고객 계좌 (001-0000067-6767)의 최종 잔액 400,000으로 업데이트
UPDATE Account
SET balance = 400000
WHERE id = '001-0000067-6767';

-- 68번 고객 계좌 (001-0000068-6868)의 최종 잔액 1,000,000으로 업데이트
UPDATE Account
SET balance = 1000000
WHERE id = '001-0000068-6868';

-- 69번 고객 계좌 (001-0000069-6969)의 최종 잔액 1,800,000으로 업데이트
UPDATE Account
SET balance = 1800000
WHERE id = '001-0000069-6969';

-- 70번 고객 계좌 (001-0000070-7070)의 최종 잔액 2,700,000으로 업데이트
UPDATE Account
SET balance = 2700000
WHERE id = '001-0000070-7070';
