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

-- 남성
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이준호', '010-1111-2222', 'MALE', '901125-1234567', '서울특별시 강남구 역삼동 123-45', '1990-11-25', 2);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김태훈', '010-2222-3333', 'MALE', '850615-2345678', '부산광역시 해운대구 우동 456-78', '1985-06-15', 2);

-- 여성
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박지연', '010-3333-4444', 'FEMALE', '920301-3456789', '대구광역시 수성구 범어동 234-56', '1992-03-01', 2);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정은주', '010-4444-5555', 'FEMALE', '971215-4567890', '인천광역시 남동구 구월동 567-89', '1997-12-15', 2);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '최민아', '010-5555-6666', 'FEMALE', '880110-5678901', '경기도 성남시 분당구 정자동 789-12', '1988-01-10', 2);

-- 6번 고객
INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김태민', '010-2345-6789', 'MALE', '890123-1234567', '서울특별시 강남구 논현동 123-45', '1989-01-23', 3);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박지윤', '010-3456-7890', 'FEMALE', '760204-2345678', '서울특별시 송파구 가락동 678-90', '1976-02-04', 3);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이수민', '010-4567-8901', 'FEMALE', '950310-3456789', '서울특별시 서초구 반포동 345-67', '1995-03-10', 3);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정하늘', '010-5678-9012', 'MALE', '880521-4567890', '서울특별시 동작구 상도동 890-12', '1988-05-21', 3);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '최민호', '010-6789-0123', 'MALE', '720606-5678901', '서울특별시 마포구 상암동 123-45', '1972-06-06', 3);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '강영희', '010-7890-1234', 'FEMALE', '990417-6789012', '서울특별시 강북구 수유동 345-67', '1999-04-17', 4);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김하늘', '010-8901-2345', 'FEMALE', '880724-7890123', '서울특별시 중구 명동 456-78', '1988-07-24', 4);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이동훈', '010-9012-3456', 'MALE', '930512-8901234', '서울특별시 영등포구 신길동 567-89', '1993-05-12', 5);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '박준호', '010-0123-4567', 'MALE', '860730-9012345', '서울특별시 관악구 봉천동 678-90', '1986-07-30', 5);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '정미래', '010-1234-5678', 'FEMALE', '950812-0123456', '서울특별시 용산구 이태원동 789-12', '1995-08-12', 5);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '이현정', '010-2345-6789', 'FEMALE', '880305-1234567', '서울특별시 서대문구 연희동 890-23', '1988-03-05', 6);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '김서영', '010-3456-7890', 'FEMALE', '820620-2345678', '서울특별시 강동구 천호동 901-34', '1982-06-20', 6);

INSERT INTO Customer (id, branch_id, name, phone_number, gender, identification_code, address, birth_date, registrant_id)
VALUES (customer_seq.NEXTVAL, 1, '조민수', '010-4567-8901', 'MALE', '790811-3456789', '서울특별시 성북구 정릉동 012-45', '1979-08-11', 6);

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

-- 계좌 생성

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
VALUES (trade_seq.nextval, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 250000, 250000, 'OPEN', 'NOR', 'TRUE', '계좌개설', 1);

-- 2번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000002-2345', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 200000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 2);

-- 3번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000003-3456', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150000, 150000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 3);

-- 4번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000004-4567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 4);

-- 5번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 5);

-- 6번 해지고객 계좌

INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000006-6789', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 700000, 700000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 5);

-- 계좌 이체

-----------------    1번 고객 계좌 -> 2번 고객 계좌      ----------------------
-- 1번 고객 계좌 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 50000, 200000, 'WITHDRAWAL', 'NOR', 'FALSE', '9월 용돈', 6, '001-0000002-2345');

-- 2번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000002-2345', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 50000, 250000, 'DEPOSIT', 'NOR', 'FALSE', '9월 용돈', 6, '001-0000001-1234');


-----------------    1번 고객 계좌 -> 3번 고객 계좌      ----------------------


-- 1번 고객 계좌 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 50000, 150000, 'WITHDRAWAL', 'NOR', 'FALSE', '명절 선물', 7, '001-0000003-3456');

-- 3번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000003-3456', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 50000, 200000, 'DEPOSIT', 'NOR', 'FALSE', '명절 선물', 7, '001-0000001-1234');

----------------     5번 고객 계좌 -> 1번 고객     -------------------------

-- 5번 고객 계좌 출금 --
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000005-5678', 5, 5, TO_TIMESTAMP('2024-08-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 30000, 270000, 'WITHDRAWAL', 'NOR', 'FALSE', '깜짝 선물', 8, '001-0000001-1234');

-- 1번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '001-0000001-1234', 5, 5, TO_TIMESTAMP('2024-08-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 30000, 130000, 'DEPOSIT', 'NOR', 'FALSE', '깜짝 선물', 8, '001-0000005-5678');


-- 계좌 현금 입출금

-- 1번 계좌로 5,000원 현금 입금
INSERT INTO Trade (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.NEXTVAL, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 5000, 135000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', 9);

--2번 계좌로 5,000원 현금 입금
INSERT INTO Trade (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.NEXTVAL, '001-0000002-2345',2, 1, TO_TIMESTAMP('2024-08-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 5000, 255000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', 10);

-- 4번 계좌에서 50,000원 현금 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000004-4567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 50000, 150000, 'WITHDRAWAL', 'NOR', 'TRUE', '생활비 인출', 11);

-- 5번 계좌에서 50,000원 현금 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000005-5678', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 50000, 250000, 'WITHDRAWAL', 'NOR', 'TRUE', '복권 구매', 12);

-- 계좌 해지

-- 1번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000001-1234', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 135000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', 13);

-- 2번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000002-2345', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 255000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', 14);

-- 3번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000003-3456', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', 15);

-- 6번 해지신청 취소 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '001-0000006-6789', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', 15);


-- 계좌 상태(해지) 및 잔액 업데이트
UPDATE Account
SET status = 'CLS',
    balance = 0
WHERE id IN ('001-0000001-1234', '001-0000002-2345', '001-0000003-3456');

UPDATE Account
SET balance = 250000
WHERE id = '001-0000004-4567';

UPDATE Account
SET balance = 220000
WHERE id = '001-0000005-5678';

-- 행원 마감

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 2, 1, 'CLOSED',  10000000, 1210000, 100000, 11110000,1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 3, 1, 'CLOSED', 10000000, 0, 0, 10000000,1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 4, 1, 'CLOSED',  10000000, 0, 0, 10000000,1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 5, 1, 'CLOSED', 10000000, 0, 0, 10000000,1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 6, 1, 'CLOSED',  10000000, 0, 0, 10000000,1);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-02 00:00:00', 2, 1, 'OPEN',  11110000, 0, 590000, 10520000 ,2);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-02 00:00:00', 3, 1, 'OPEN', 10000000, 0, 0, 10000000,2);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-02 00:00:00', 4, 1, 'CLOSED',  10000000, 0, 0, 10000000,2);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-02 00:00:00', 5, 1, 'CLOSED', 10000000, 0, 0, 10000000,2);

INSERT INTO EMPLOYEE_CLOSING (closing_date, registrant_id, branch_id, status, prev_cash_balance, total_deposit, total_withdrawal, vault_cash, trade_number)
VALUES('2024-08-02 00:00:00', 6, 1, 'CLOSED',  10000000, 0, 0, 10000000,2);

INSERT INTO BRANCH_CLOSING (closing_date,registrant_id, branch_id,  status, prev_cash_balance, vault_cash, trade_number)
VALUES('2024-08-01 00:00:00', 2, 1, 'CLOSED',  50000000,  51110000,1);

INSERT INTO BRANCH_CLOSING (closing_date,registrant_id, branch_id,  status, prev_cash_balance, vault_cash, trade_number)
VALUES('2024-08-02 00:00:00', 2, 1, 'OPEN',   51110000,  50520000,2);


-----------------------------------------거래내역을 위한 sql---------------------------------------------------------
/*

     --계좌 개설
*/

--  계좌 생성 : 회사A(88), 사원(77)
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('7734567890123', 1, 5, 5, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 80000000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('8834567890123', 1, 5, 5, 2, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 10000000, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OPN', 1);


-- 계좌 생성 : 회사 B (66) - 알바 90, 91 ~ 99 (10명)
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('6634567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 100000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9034567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 10, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9134567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9234567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9334567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9434567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9534567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9634567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9734567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9834567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('9934567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);



-- 1월 송금
-- 회사에서 사원 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-01-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 75000000, 'WITHDRAWAL', 'NOR', 'FALSE', '1월 월급 송금', 1, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-01-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 15000000, 'DEPOSIT', 'NOR', 'FALSE', '1월 월급 입금', 1, '8834567890123');

-- 2월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-02-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 70000000, 'WITHDRAWAL', 'NOR', 'FALSE', '2월 월급 송금', 2, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-02-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 20000000, 'DEPOSIT', 'NOR', 'FALSE', '2월 월급 입금', 2, '8834567890123');

-- 3월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-03-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 65000000, 'WITHDRAWAL', 'NOR', 'FALSE', '3월 월급 송금', 3, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-03-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 25000000, 'DEPOSIT', 'NOR', 'FALSE', '3월 월급 입금', 3, '8834567890123');

-- 4월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-04-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 60000000, 'WITHDRAWAL', 'NOR', 'FALSE', '4월 월급 송금', 4, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-04-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 30000000, 'DEPOSIT', 'NOR', 'FALSE', '4월 월급 입금', 4, '8834567890123');

-- 5월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-05-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 55000000, 'WITHDRAWAL', 'NOR', 'FALSE', '5월 월급 송금', 5, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-05-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 35000000, 'DEPOSIT', 'NOR', 'FALSE', '5월 월급 입금', 5, '8834567890123');



-- 6월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-06-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 50000000, 'WITHDRAWAL', 'NOR', 'FALSE', '6월 월급 송금', 6, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-06-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 40000000, 'DEPOSIT', 'NOR', 'FALSE', '6월 월급 입금', 6, '8834567890123');

-- 7월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-07-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 45000000, 'WITHDRAWAL', 'NOR', 'FALSE', '7월 월급 송금', 7, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-07-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 45000000, 'DEPOSIT', 'NOR', 'FALSE', '7월 월급 입금', 7, '8834567890123');

-- 8월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-08-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 40000000, 'WITHDRAWAL', 'NOR', 'FALSE', '8월 월급 송금', 8, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-08-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 50000000, 'DEPOSIT', 'NOR', 'FALSE', '8월 월급 입금', 8, '8834567890123');

-- 9월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-09-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 35000000, 'WITHDRAWAL', 'NOR', 'FALSE', '9월 월급 송금', 9, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-09-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 55000000, 'DEPOSIT', 'NOR', 'FALSE', '9월 월급 입금', 9, '8834567890123');

-- 10월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-10-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 30000000, 'WITHDRAWAL', 'NOR', 'FALSE', '10월 월급 송금', 10, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-10-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 60000000, 'DEPOSIT', 'NOR', 'FALSE', '10월 월급 입금', 10, '8834567890123');

-- 11월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-11-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 25000000, 'WITHDRAWAL', 'NOR', 'FALSE', '11월 월급 송금', 11, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-11-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 65000000, 'DEPOSIT', 'NOR', 'FALSE', '11월 월급 입금', 11, '8834567890123');

-- 12월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2024-12-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 20000000, 'WITHDRAWAL', 'NOR', 'FALSE', '12월 월급 송금', 12, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2024-12-11 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 70000000, 'DEPOSIT', 'NOR', 'FALSE', '12월 월급 입금', 12,'8834567890123');

-- 12월 송금
-- 회사에서 부모님에게 5000000원 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '8834567890123', 1, 1, TO_TIMESTAMP('2023-01-15 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 20000000, 'WITHDRAWAL', 'NOR', 'FALSE', '12월 월급 송금', 12, '7734567890123');
-- 부모님 계좌에 5000000원 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '7734567890123', 1, 1, TO_TIMESTAMP('2023-01-15 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 5000000, 70000000, 'DEPOSIT', 'NOR', 'FALSE', '12월 월급 입금', 12,'8834567890123');



/*
    8834567890123 계좌 (회사 A): 초기 금액 8000만 원, 매달 500만 원을 7734567890123 계좌로 이체.
    7734567890123 계좌 (사원1): 초기 금액 1000만 원, 매달 100만 원을 6634567890123 계좌로 이체.

    6634567890123 계좌 (회사 B ): 초기 금액 1억, 매달 15일 100만 원을 알바 91 ~ 99 계좌로 이체.
    B 회사 사원 계좌 (알바91,92,93,94,95,96,97,98,99): 초기 금액 0만 원, 매달 10만 원을 이체
    B 회사 직원
        9034567890123,
        9134567890123,
        9234567890123,
        9334567890123,
        9434567890123,
        9534567890123,
        9634567890123,
        9734567890123,
        9834567890123,
        9934567890123
    */
-- 1월 송금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '6634567890123', 1, 1, TO_TIMESTAMP('2024-01-02 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 10, 99990, 'WITHDRAWAL', 'NOR', 'FALSE', '송금', 1, '9034567890123');

-- 알바 계좌 9034567890123 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '9034567890123', 1, 1, TO_TIMESTAMP('2024-01-02 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 10, 20, 'DEPOSIT', 'NOR', 'FALSE', '입금', 1, '6634567890123');

-- 3
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '6634567890123', 1, 1, TO_TIMESTAMP('2024-01-03 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 10, 99980, 'WITHDRAWAL', 'NOR', 'FALSE', '송금', 1, '9034567890123');
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '9034567890123', 1, 1, TO_TIMESTAMP('2024-01-03 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 10, 30, 'DEPOSIT', 'NOR', 'FALSE', '입금', 1, '6634567890123');


-- 4
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '6634567890123', 1, 1, TO_TIMESTAMP('2024-01-04 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 10, 99970, 'WITHDRAWAL', 'NOR', 'FALSE', '송금', 1, '9034567890123');
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '9034567890123', 1, 1, TO_TIMESTAMP('2024-01-04 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 10, 40, 'DEPOSIT', 'NOR', 'FALSE', '입금', 1, '6634567890123');


-------------------------------------------------- 입출금 66 90 --------------------------------------------------








------------------------------------------------------------------------------------------------------------------------------------------------------
