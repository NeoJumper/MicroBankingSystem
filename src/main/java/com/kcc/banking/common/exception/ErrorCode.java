package com.kcc.banking.common.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {


    // 400 : 입력값이 잘못된 경우 / 문법상 오류 / 서버가 요청 구문 인식 X
    EMPTY_REFRESH_TOKEN("RefreshToken이 필요합니다.", HttpStatus.BAD_REQUEST),
    EMPTY_EMAIL("이메일이 필요합니다.", HttpStatus.BAD_REQUEST),
    INVALID_CODE("not invalid Code",HttpStatus.BAD_REQUEST ),
    ACCOUNT_CLOSED_FOR_TRANSFER("해지된 계좌에 대한 거래가 불가능합니다.", HttpStatus.BAD_REQUEST),
    OVER_TRANSFER_AMOUNT("계좌 잔액보다 이체 금액이 더 많습니다.", HttpStatus.BAD_REQUEST),
    INVALID_ACCOUNT_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    CLS_ACCOUNT("해지된 계좌에 대한 거래가 불가능합니다.", HttpStatus.BAD_REQUEST),
    OVER_AMOUNT("계좌 잔액보다 이체 금액이 더 많습니다.", HttpStatus.BAD_REQUEST),
    OVER_PER_TRADE_LIMIT("1회 이체 한도를 초과했습니다.", HttpStatus.BAD_REQUEST),
    OVER_DAILY_LIMIT("1일 이체 한도를 초과했습니다.", HttpStatus.BAD_REQUEST),
    ALREADY_CHANGED_BUSINESS_DAY("이미 변경된 영업일입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_CLOSED_BUSINESS_DAY("이미 마감된 영업일입니다.", HttpStatus.BAD_REQUEST),
    CLOSED_BUSINESS_DAY("영업일이 마감되었습니다.", HttpStatus.BAD_REQUEST),
    REQUIRED_BRANCH_CLOSING("지점 마감이 처리되지 않았습니다.", HttpStatus.BAD_REQUEST),
    REQUIRED_EMPLOYEE_CLOSING("개인마감을 진행중인 사원이 존재합니다.", HttpStatus.BAD_REQUEST),
    REQUIRED_UPDATE_TRANSFER_CANCEL("이체 상태 변경이 처리되지 않았습니다.", HttpStatus.BAD_REQUEST),

    // 403

    NOT_INSERT_ACCOUNT_ID("계좌번호가 생성되지 않았습니다.", HttpStatus.BAD_REQUEST),
    NOT_INSERT_BALANCE("잔액이 입력되지 않았습니다.", HttpStatus.BAD_REQUEST),
    NOT_INSERT_PRODUCT("상품을 선택되지 않았습니다.", HttpStatus.BAD_REQUEST),
    NOT_INSERT_PASSWORD("계좌 비밀번호가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST),
    NOT_INSERT_PREFERENTIAL_INTEREST_RATE(" 우대이율을 입력하지 않았습니다.", HttpStatus.BAD_REQUEST),
    NOT_INSERT_CUSTOMER_ID("고객번호가 입력하지 않았습니다.", HttpStatus.BAD_REQUEST),


    // 401 : 접근 권한이 없는 경우/ 인증이 필요한데 인증이 되지 않은 경우

    // 403 : 접근 금지 / 요청 거부
    NOT_LOGIN("로그인 후 이용할 수 있습니다.", HttpStatus.FORBIDDEN),
    NOT_OPEN("영업 마감된 상태입니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_CREATE("생성 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_DELETE("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_UPDATE("수정 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // 404 : 요청 페이지 못찾음 / 잘못된 URL이거나 없는 페이지를 불러올때
    NOT_FOUND_MEMBER("회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ACCOUNT("존재하지 않는 계좌번호입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_TARGET_ACCOUNT("상대 계좌가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_TRADE_NUMBER("존재하지 않는 거래번호입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_BUSINESS_DATE("영업일이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_EMPLOYEE_ID("직원번호가 존재하지 않습니다.", HttpStatus.NOT_FOUND),



    // 409
    ALREADY_JOINED("이미 존재하는 회원입니다.", HttpStatus.CONFLICT),

    //500 : 서버에 오류 / 요청 수행 할수 없음
    FAIL_UPLOAD_S3("AWS S3 업로드 실패!", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}