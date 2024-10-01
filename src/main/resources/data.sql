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
VALUES (employee_seq.NEXTVAL,  '김철수','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-1111-1111', '매니저', 1);

-- 지점 1 행원
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '김하늘','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-5678-9101', '행원', 1);

INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이수진', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-2345-6789', '행원', 1);

INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '박준호', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'test1@gmail.com','010-3456-7890', '행원', 1);

INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정미래','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'test1@gmail.com','010-4567-8901', '행원', 1);

INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '최유리','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-5678-9012', '행원', 1);

-- 지점 2 매니저
INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '박영희','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-2222-2222', '매니저', 2);

-- 지점 2 행원
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '임도현','1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-6789-0123', '행원', 2);


-- 지점 3 매니저
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이민호', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-3333-3333', '매니저', 3);
-- 지점 3 행원
INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '홍서연', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'test1@gmail.com','010-7890-1234', '행원', 3);


-- 지점 4 매니저
INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '최지훈', '1999-02-14', '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-4444-4444', '매니저', 4);
-- 지점 4 행원
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정세진', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-8901-2345', '행원', 4);


-- 지점 5 매니저
INSERT INTO Employee (id, name,birth_date,  password, email, phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '정유진', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-5555-5555', '매니저', 5);

-- 지점 5 행원


INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '민정호', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 'test1@gmail.com','010-9012-3456', '행원', 5);

INSERT INTO Employee (id, name,birth_date,  password,email,  phone_number, roles, branch_id)
VALUES (employee_seq.NEXTVAL, '이경호', '1999-02-14','$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6','test1@gmail.com', '010-0123-4567', '행원', 5);

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

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('1234567890123', 1, 1, 1, 2, SYSDATE, 0.5, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 250000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('2345678901234', 1, 2, 2, 2, SYSDATE, 0.7, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 200000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('3456789012345', 1, 3, 3, 2, SYSDATE, 1.0, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 150000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('4567890123456', 1, 4, 4, 2, SYSDATE, 0.3, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);

INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('5678901234567', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);


-- 1번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '1234567890123', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 250000, 250000, 'OPEN', 'NOR', 'TRUE', '계좌개설', 1);

-- 2번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '2345678901234', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 200000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 2);

-- 3번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '3456789012345', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150000, 150000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 3);

-- 4번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '4567890123456', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 4);

-- 5번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '5678901234567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 300000, 300000, 'OPEN', 'NOR', 'TRUE',  '계좌개설', 5);

-----------------    1번 고객 계좌 -> 2번 고객 계좌      ----------------------
-- 1번 고객 계좌 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '1234567890123', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 50000, 200000, 'WITHDRAWAL', 'NOR', 'FALSE', '9월 용돈', 6, '2345678901234');

-- 2번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '2345678901234', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 50000, 250000, 'DEPOSIT', 'NOR', 'FALSE', '9월 용돈', 6, '1234567890123');


-----------------    1번 고객 계좌 -> 3번 고객 계좌      ----------------------


-- 1번 고객 계좌 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '1234567890123', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 50000, 150000, 'WITHDRAWAL', 'NOR', 'FALSE', '명절 선물', 7, '3456789012345');

-- 3번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '3456789012345', 1, 1, TO_TIMESTAMP('2024-08-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 50000, 200000, 'DEPOSIT', 'NOR', 'FALSE', '명절 선물', 7, '1234567890123');

----------------     5번 고객 계좌 -> 1번 고객     -------------------------

-- 5번 고객 계좌 출금 --
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '5678901234567', 5, 5, TO_TIMESTAMP('2024-08-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 30000, 270000, 'WITHDRAWAL', 'NOR', 'FALSE', '깜짝 선물', 8, '1234567890123');

-- 1번 고객 계좌 입금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '1234567890123', 5, 5, TO_TIMESTAMP('2024-08-01 00:00:03', 'YYYY-MM-DD HH24:MI:SS'), 30000, 130000, 'DEPOSIT', 'NOR', 'FALSE', '깜짝 선물', 8, '5678901234567');

-- 1번 계좌로 5,000원 현금 입금
INSERT INTO Trade (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.NEXTVAL, '1234567890123', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:04', 'YYYY-MM-DD HH24:MI:SS'), 5000, 135000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', 9);

--2번 계좌로 5,000원 현금 입금
INSERT INTO Trade (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.NEXTVAL, '2345678901234',2, 1, TO_TIMESTAMP('2024-08-01 00:00:05', 'YYYY-MM-DD HH24:MI:SS'), 5000, 255000, 'DEPOSIT', 'NOR', 'TRUE', '용돈', 10);

-- 4번 계좌에서 50,000원 현금 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '4567890123456', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:06', 'YYYY-MM-DD HH24:MI:SS'), 50000, 150000, 'WITHDRAWAL', 'NOR', 'TRUE', '생활비 인출', 11);

-- 5번 계좌에서 50,000원 현금 출금
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '5678901234567', 2, 1, TO_TIMESTAMP('2024-08-01 00:00:07', 'YYYY-MM-DD HH24:MI:SS'), 50000, 250000, 'WITHDRAWAL', 'NOR', 'TRUE', '복권 구매', 12);

-- 1번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '1234567890123', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 135000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', 13);

-- 2번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '2345678901234', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 255000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', 14);

-- 3번 고객 계좌
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '3456789012345', 2, 1, TO_TIMESTAMP('2024-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 200000, 0, 'CLOSE', 'NOR', 'TRUE', '계좌해지', 15);

-- 계좌 상태(해지) 및 잔액 업데이트
UPDATE Account
SET status = 'CLS',
    balance = 0
WHERE id IN ('1234567890123', '2345678901234', '3456789012345');

UPDATE Account
SET balance = 250000
WHERE id = 4567890123456;

UPDATE Account
SET balance = 220000
WHERE id = 5678901234567;


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
INSERT INTO Account (id, branch_id, customer_id, product_id, registrant_id, start_date, preferential_interest_rate, expire_date, password, balance, open_date, status, version)
VALUES ('6634567890123', 1, 5, 5, 2, SYSDATE, 0.9, NULL, '$2a$12$KEC0twTfMAlrbchL4p4lPOyX7/n0Q/eNZjsLkA0yY5j.udeV6MiO6', 300000, SYSDATE, 'OPN', 1);


-- 6634567890123번 고객 계좌 2024-01-01
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number)
VALUES (trade_seq.nextval, '6634567890123', 2, 1, TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 250000, 250000, 'OPEN', 'NOR', 'TRUE', '계좌개설', 1);

-- 6634567890123번 고객 계좌 출금 -20000 2024-01-02
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '6634567890123', 1, 1, TO_TIMESTAMP('2024-01-02 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 20000, 180000, 'WITHDRAWAL', 'NOR', 'FALSE', '9월 용돈', 6, '2345678901234');

-- 6634567890123번 고객 계좌 출금 2024-04-01
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '6634567890123', 1, 1, TO_TIMESTAMP('2024-04-01 00:00:01', 'YYYY-MM-DD HH24:MI:SS'), 80000, 100000, 'WITHDRAWAL', 'NOR', 'FALSE', '9월 용돈', 6, '2345678901234');

-- 6634567890123번 고객 계좌 입금 2024-07-01
INSERT INTO TRADE (id, acc_id, registrant_id, branch_id, trade_date, amount, balance, trade_type, status, cash_indicator, description, trade_number, target_acc_id)
VALUES (trade_seq.nextval, '6634567890123', 1, 1, TO_TIMESTAMP('2024-07-01 00:00:02', 'YYYY-MM-DD HH24:MI:SS'), 150000, 200000, 'DEPOSIT', 'NOR', 'FALSE', '명절 선물', 7, '1234567890123');
