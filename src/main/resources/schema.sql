-- 시퀀스 삭제
drop sequence branch_cls_seq;
drop sequence emp_cls_seq;
drop sequence employee_seq;
drop sequence business_day_seq;
drop sequence branch_seq;
drop sequence trade_seq;
drop sequence trade_num_seq;
drop sequence interest_seq;
drop sequence product_seq;
drop sequence customer_seq;
drop sequence account_seq;
drop sequence auto_transfer_seq;
drop sequence bulk_transfer_seq;
drop sequence cash_exchange_seq;

-- 테이블 삭제
DROP TABLE BRANCH_CLOSING CASCADE CONSTRAINTS PURGE;
DROP TABLE EMPLOYEE_CLOSING CASCADE CONSTRAINTS PURGE;
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS PURGE;
DROP TABLE BRANCH CASCADE CONSTRAINTS PURGE;
DROP TABLE TRADE CASCADE CONSTRAINTS PURGE;
DROP TABLE PRODUCT CASCADE CONSTRAINTS PURGE;
DROP TABLE BUSINESS_DAY CASCADE CONSTRAINTS PURGE;
DROP TABLE INTEREST CASCADE CONSTRAINTS PURGE;
DROP TABLE CUSTOMER CASCADE CONSTRAINTS PURGE;
DROP TABLE ACCOUNT CASCADE CONSTRAINTS PURGE;
DROP TABLE AUTO_TRANSFER CASCADE CONSTRAINTS PURGE;
DROP TABLE BULK_TRANSFER CASCADE CONSTRAINTS PURGE;
DROP TABLE Cash_exchange CASCADE CONSTRAINTS PURGE;

CREATE TABLE Employee (
                          id NUMBER NOT NULL,
                          registrant_id NUMBER NULL,
                          branch_id NUMBER NOT NULL,
                          birth_date TIMESTAMP NULL,
                          email VARCHAR(100) NOT NULL,
                          name VARCHAR(100) NOT NULL,
                          password VARCHAR(1000) NOT NULL,
                          phone_number VARCHAR(13) NULL,
                          roles VARCHAR(20) NULL,
                          registration_date TIMESTAMP NULL,
                          modification_date TIMESTAMP NULL,
                          modifier_id NUMBER NULL,
                          version NUMBER NULL
);

CREATE TABLE Trade (
                       id NUMBER NOT NULL,
                       registrant_id NUMBER NOT NULL,
                       acc_id VARCHAR(20) NOT NULL,
                       target_acc_id VARCHAR(20) NULL,
                       bulk_transfer_id VARCHAR(20) NULL,
                       branch_id NUMBER NOT NULL,
                       trade_date TIMESTAMP NULL,
                       amount NUMBER NULL,
                       balance NUMBER NULL,
                       trade_type VARCHAR(255) NULL,
                       status VARCHAR(10) NULL,
                       cash_indicator VARCHAR(10) NULL,
                       failure_reason VARCHAR(100) NULL,
                       description VARCHAR(1000) NULL,
                       trade_number NUMBER NOT NULL,
                       registration_date TIMESTAMP NULL,
                       modification_date TIMESTAMP NULL,
                       modifier_id NUMBER NULL,
                       version NUMBER NULL
);

CREATE TABLE Bulk_transfer (
                       id NUMBER NOT NULL,
                       acc_id VARCHAR(20) NOT NULL,
                       branch_id NUMBER NOT NULL,
                       trade_date TIMESTAMP NULL,
                       amount NUMBER NULL,
                       status VARCHAR(10) NULL,
                       description VARCHAR(1000) NULL,
                       success_cnt NUMBER NOT NULL ,
                       failure_cnt NUMBER NOT NULL ,
                       registrant_id NUMBER NOT NULL,
                       registration_date TIMESTAMP NULL,
                       modification_date TIMESTAMP NULL,
                       modifier_id NUMBER NULL,
                       version NUMBER NULL
);



CREATE TABLE Customer (
                          id NUMBER NOT NULL,
                          registrant_id NUMBER NOT NULL,
                          branch_id NUMBER NOT NULL,
                          name VARCHAR(100) NULL,
                          email VARCHAR(100) NULL,
                          phone_number VARCHAR(20) NULL,
                          gender VARCHAR(10) NULL,
                          identification_code VARCHAR(15) NULL,
                          address VARCHAR(2000) NULL,
                          detail_address VARCHAR(2000) NULL,
                          birth_date TIMESTAMP NULL,
                          security_level VARCHAR(10) NULL,
                          otp_key VARCHAR(20) NULL,
                          registration_date TIMESTAMP NULL,
                          registrant VARCHAR(100) NULL,
                          modification_date TIMESTAMP NULL,
                          modifier_id NUMBER NULL,
                          version NUMBER NULL
);

CREATE TABLE Auto_transfer (
                         id NUMBER NOT NULL,
                        -- 출금할 계좌
                         acc_id  VARCHAR(20) NOT NULL,
                        -- 입금할 계좌
                         target_acc_id  VARCHAR(20) NOT NULL,
                         amount NUMBER NOT NULL,
                         auto_transfer_start_date TIMESTAMP NULL,
                         auto_transfer_date TIMESTAMP NULL,
                         auto_transfer_end_date TIMESTAMP NULL,
                         auto_transfer_period NUMBER NULL,
                         create_date TIMESTAMP NULL,
                         registration_date TIMESTAMP NULL,
                         registrant_id NUMBER NULL,
                         modification_date TIMESTAMP NULL,
                         modifier_id NUMBER NULL,
                         version NUMBER NULL
);

CREATE TABLE Account (
                         id VARCHAR(20) NOT NULL,
                         branch_id NUMBER NOT NULL,
                         registrant_id NUMBER NOT NULL,
                         customer_id NUMBER NOT NULL,
                         product_id NUMBER NOT NULL,
                         start_date TIMESTAMP NULL,
                         preferential_interest_rate NUMBER NULL,
                         expire_date TIMESTAMP NULL,
                         password VARCHAR(1000) NULL,
                         balance DECIMAL NULL,
                         open_date TIMESTAMP NULL,
                         per_trade_limit DECIMAL NULL,
                         daily_limit DECIMAL NULL,
                         account_type VARCHAR(20) NULL,
                         status VARCHAR(6) NULL,
                         trade_number NUMBER NULL,
                         registration_date TIMESTAMP NULL,
                         modification_date TIMESTAMP NULL,
                         modifier_id NUMBER NULL,
                         version NUMBER NULL
);


CREATE TABLE Interest (
                          id NUMBER NOT NULL,
                          acc_id VARCHAR(20) NOT NULL,
                          registrant_id NUMBER NOT NULL,
                          branch_id NUMBER NOT NULL,
                          payment_date TIMESTAMP NULL,
                          creation_date TIMESTAMP NULL,
                          amount DECIMAL NULL,
                          balance DECIMAL NULL,
                          interest_rate NUMBER NULL,
                          preferential_interest_rate NUMBER NULL,
                          payment_status VARCHAR(1) NULL,
                          trade_number NUMBER NULL,
                          registration_date TIMESTAMP NULL,
                          modifier_date TIMESTAMP NULL,
                          modifier_id NUMBER NULL,
                          version NUMBER NULL
);


CREATE TABLE Branch (
                        id NUMBER NOT NULL,
                        registrant_id NUMBER NULL,
                        name VARCHAR(100) NULL,
                        address VARCHAR(1000) NULL,
                        phone_number VARCHAR(13) NULL,
                        vault_cash DECIMAL NULL,
                        registration_date TIMESTAMP NULL,
                        modification_date TIMESTAMP NULL,
                        modifier_id NUMBER NULL,
                        version NUMBER NULL
);

CREATE TABLE Business_day (
                              business_date DATE NOT NULL,
                              status VARCHAR(255) NULL,
                              is_current_business_day VARCHAR(10),
                              modification_date TIMESTAMP NULL,
                              modifier_id NUMBER,
                              version NUMBER NULL
);

CREATE TABLE Product (
                         id NUMBER NOT NULL,
                         registrant_id NUMBER NOT NULL,
                         branch_id NUMBER NOT NULL,
                         name VARCHAR(100) NULL,
                         interest_rate NUMBER NULL,
                         effective_date TIMESTAMP NULL,
                         period VARCHAR(10) NULL,
                         tax_rate NUMBER NULL,
                         account_type VARCHAR(20) NULL,
                         registration_date TIMESTAMP NULL,
                         modification_date TIMESTAMP NULL,
                         modifier_id NUMBER NULL,
                         version NUMBER NULL,
                         product_type VARCHAR(10) NULL,
                         interest_calculation_method VARCHAR(20) NULL
                         );

CREATE TABLE Branch_closing (
                                closing_date TIMESTAMP NOT NULL,
                                branch_id NUMBER NOT NULL,
                                registrant_id NUMBER NOT NULL,
                                status VARCHAR(6) NULL,
                                prev_cash_balance DECIMAL NULL,
                                vault_cash DECIMAL NULL,
                                trade_number NUMBER NULL,
                                registration_date TIMESTAMP NULL,
                                modification_date TIMESTAMP NULL,
                                modifier_id NUMBER NULL,
                                version NUMBER NULL
);

CREATE TABLE Employee_closing (
                                  closing_date TIMESTAMP NOT NULL,
                                  registrant_id NUMBER NULL,
                                  branch_id NUMBER NOT NULL,
                                  status VARCHAR(6) NULL,
                                  prev_cash_balance DECIMAL NULL,
                                  total_deposit DECIMAL NULL,
                                  total_withdrawal DECIMAL NULL,
                                  vault_cash DECIMAL NULL,
                                  trade_number NUMBER NULL,
                                  registration_date TIMESTAMP NULL,
                                  modification_date TIMESTAMP NULL,
                                  modifier_id NUMBER NULL,
                                  version NUMBER NULL
);


CREATE TABLE Cash_exchange (
                               id NUMBER NOT NULL,
                               registrant_id NUMBER NOT NULL,
                               emp_id NUMBER NOT NULL,
                               branch_id NUMBER NOT NULL,
                               amount DECIMAL NOT NULL,
                               emp_cash_balance DECIMAL,
                               manager_cash_balance DECIMAL,
                               exchange_type VARCHAR(10) NOT NULL,
                               exchange_date TIMESTAMP NULL,
                               registration_date TIMESTAMP NULL,
                               modification_date TIMESTAMP NULL,
                               modifier_id NUMBER NULL,
                               version NUMBER NULL
);


-- Primary Keys
ALTER TABLE Employee ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (id);
ALTER TABLE trade ADD CONSTRAINT PK_TRADE PRIMARY KEY (id);
ALTER TABLE bulk_transfer ADD CONSTRAINT PK_BULK_TRANSFER PRIMARY KEY (id);
ALTER TABLE Customer ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY (id);
ALTER TABLE Account ADD CONSTRAINT PK_ACCOUNT PRIMARY KEY (id);
ALTER TABLE Interest ADD CONSTRAINT PK_INTEREST PRIMARY KEY (id);
ALTER TABLE Branch ADD CONSTRAINT PK_BRANCH PRIMARY KEY (id);
ALTER TABLE Product ADD CONSTRAINT PK_PRODUCT PRIMARY KEY (id);
ALTER TABLE Branch_closing ADD CONSTRAINT PK_BRANCH_CLOSING PRIMARY KEY (closing_date, registrant_id);
ALTER TABLE Employee_closing ADD CONSTRAINT PK_EMPLOYEE_CLOSING PRIMARY KEY (closing_date, registrant_id);
ALTER TABLE Cash_exchange ADD CONSTRAINT PK_CASH_EXCHANGE PRIMARY KEY (id);

-- Foreign Keys
-- 1. Employee
ALTER TABLE Employee
    ADD CONSTRAINT FK_EMP_BRNCH_ID
        FOREIGN KEY (branch_id) REFERENCES Branch(id);

ALTER TABLE Employee
    ADD CONSTRAINT FK_EMP_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES Employee(id);

ALTER TABLE Employee
    ADD CONSTRAINT FK_EMP_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES Employee(id);


--2. trade
ALTER TABLE Trade
    ADD CONSTRAINT FK_TRADE_ACC_ID
        FOREIGN KEY (acc_id) REFERENCES Account(id);

ALTER TABLE Trade
    ADD CONSTRAINT FK_TRADE_TARG_ID
        FOREIGN KEY (target_acc_id) REFERENCES Account(id);

ALTER TABLE Trade
    ADD CONSTRAINT FK_TRADE_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES Employee(id);

ALTER TABLE Trade
    ADD CONSTRAINT FK_TRADE_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES Employee(id);

ALTER TABLE Trade
    ADD CONSTRAINT FK_TRADE_BRANCH_ID
        FOREIGN KEY (branch_id) REFERENCES Branch(id);


--3. account
ALTER TABLE ACCOUNT
    ADD CONSTRAINT FK_ACC_BRNCH_ID
        FOREIGN KEY (branch_id) REFERENCES BRANCH(id);

ALTER TABLE ACCOUNT
    ADD CONSTRAINT FK_ACC_CUSTOMER_ID
        FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id);

ALTER TABLE ACCOUNT
    ADD CONSTRAINT FK_ACC_PRODUCT_ID
        FOREIGN KEY (product_id) REFERENCES PRODUCT(id);

ALTER TABLE ACCOUNT
    ADD CONSTRAINT FK_ACC_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES EMPLOYEE(id);

ALTER TABLE ACCOUNT
    ADD CONSTRAINT FK_ACC_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES Employee(id);

-- 4. product
ALTER TABLE PRODUCT
    ADD CONSTRAINT FK_PRODUCT_BRNCH_ID
        FOREIGN KEY (branch_id) REFERENCES BRANCH(id);

ALTER TABLE PRODUCT
    ADD CONSTRAINT FK_PRODUCT_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES EMPLOYEE(id);

ALTER TABLE PRODUCT
    ADD CONSTRAINT FK_PRODUCT_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES Employee(id);

-- 5. Customer
ALTER TABLE Customer
    ADD CONSTRAINT FK_CUSTOMER_BRNCH_ID
        FOREIGN KEY (branch_id) REFERENCES Branch(id);

ALTER TABLE Customer
    ADD CONSTRAINT FK_CUSTOMER_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES EMPLOYEE(id);

ALTER TABLE Customer
    ADD CONSTRAINT FK_CUSTOMER_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES EMPLOYEE(id);

-- 6. Interest
-- Interest 테이블의 registrant_id는 Employee 테이블의 id
ALTER TABLE Interest
    ADD CONSTRAINT FK_INTEREST_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES Employee(id);

ALTER TABLE Interest
    ADD CONSTRAINT FK_INTEREST_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES Employee(id);

ALTER TABLE Interest
    ADD CONSTRAINT FK_INT_ACC_ID
        FOREIGN KEY (acc_id) REFERENCES Account(id);

ALTER TABLE Interest
    ADD CONSTRAINT FK_INT_BRNCH_ID
        FOREIGN KEY (branch_id) REFERENCES Branch(id);

-- 7. Branch_closing
ALTER TABLE BRANCH_CLOSING
    ADD CONSTRAINT FK_BC_CLS_BRNCH_ID
        FOREIGN KEY (branch_id) REFERENCES BRANCH(id);

ALTER TABLE BRANCH_CLOSING
    ADD CONSTRAINT FK_BC_CLS_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES EMPLOYEE(id);

ALTER TABLE BRANCH_CLOSING
    ADD CONSTRAINT FK_BC_CLS_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES EMPLOYEE(id);

-- 8. Employee-closing
ALTER TABLE Employee_closing
    ADD CONSTRAINT FK_EMP_CLS_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES Employee(id);

ALTER TABLE Employee_closing
    ADD CONSTRAINT FK_EMP_CLS_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES Employee(id);

-- 9. Branch
ALTER TABLE BRANCH
    ADD CONSTRAINT FK_BC_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES EMPLOYEE(id);

ALTER TABLE BRANCH
    ADD CONSTRAINT FK_BC_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES EMPLOYEE(id);

--10. Bulk_Transfer
ALTER TABLE Bulk_transfer
    ADD CONSTRAINT FK_BT_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES Employee(id);

ALTER TABLE Bulk_transfer
    ADD CONSTRAINT FK_BT_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES Employee(id);

ALTER TABLE Bulk_transfer
    ADD CONSTRAINT FK_BT_ACC_ID
        FOREIGN KEY (acc_id) REFERENCES Account(id);

ALTER TABLE Bulk_transfer
    ADD CONSTRAINT FK_BT_BRANCH_ID
        FOREIGN KEY (branch_id) REFERENCES Branch(id);


-- 자동이체 테이블 생성
ALTER TABLE Auto_transfer
    ADD CONSTRAINT FK_AUTO_TRANSFER_ACC_ID
        FOREIGN KEY (acc_id) REFERENCES Account(id);

ALTER TABLE Auto_transfer
    ADD CONSTRAINT FK_AUTO_TRANSFER_TARG_ID
        FOREIGN KEY (target_acc_id) REFERENCES Account(id);

ALTER TABLE Auto_transfer
    ADD CONSTRAINT FK_AUTO_TRANSFER_MOD_ID
        FOREIGN KEY (modifier_id) REFERENCES Employee(id);

ALTER TABLE Auto_transfer
    ADD CONSTRAINT FK_AUTO_TRANSFER_REG_ID
        FOREIGN KEY (registrant_id) REFERENCES EMPLOYEE(id);

-- cash_exchange 테이블 fk 생성
ALTER TABLE Cash_exchange
    ADD CONSTRAINT FK_CE_REG_ID
    FOREIGN KEY (registrant_id) REFERENCES Employee(id);

ALTER TABLE Cash_exchange
    ADD CONSTRAINT FK_CE_EMP_ID
        FOREIGN KEY (emp_id) REFERENCES Employee(id);

ALTER TABLE Cash_exchange
    ADD CONSTRAINT FK_CE_BRANCH_ID
        FOREIGN KEY (branch_id) REFERENCES Branch(id);

-- 시퀀스 생성
create sequence branch_cls_seq;
create sequence emp_cls_seq;
create sequence employee_seq;
create sequence business_day_seq;
create sequence branch_seq;
create sequence trade_seq;
CREATE SEQUENCE trade_num_seq;
create sequence interest_seq;
create sequence product_seq;
create sequence customer_seq;
create sequence account_seq;
create sequence auto_transfer_seq;
create sequence bulk_transfer_seq;
create sequence cash_exchange_seq;
