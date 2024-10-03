package com.kcc.banking.common.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 400
    EMPTY_REFRESH_TOKEN("RefreshToken이 필요합니다.", HttpStatus.BAD_REQUEST),
    EMPTY_EMAIL("이메일이 필요합니다.", HttpStatus.BAD_REQUEST),
    INVALID_CODE("not invalid Code",HttpStatus.BAD_REQUEST ),
    ACCOUNT_CLOSED_FOR_TRANSFER("해지된 계좌에 대한 거래가 불가능합니다.", HttpStatus.BAD_REQUEST),
    OVER_TRANSFER_AMOUNT("계좌 잔액보다 이체 금액이 더 많습니다.", HttpStatus.BAD_REQUEST),
    INVALID_ACCOUNT_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    CLS_ACCOUNT("해지된 계좌에 대한 거래가 불가능합니다.", HttpStatus.BAD_REQUEST),
    OVER_AMOUNT("계좌 잔액보다 이체 금액이 더 많습니다.", HttpStatus.BAD_REQUEST),
    ALREADY_CHANGED_BUSINESS_DAY("이미 변경된 영업일입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_CLOSED_BUSINESS_DAY("이미 마감된 영업일입니다.", HttpStatus.BAD_REQUEST),
    REQUIRED_BRANCH_CLOSING("지점 마감이 처리되지 않았습니다.", HttpStatus.BAD_REQUEST),
    REQUIRED_EMPLOYEE_CLOSING("개인마감을 진행중인 사원이 존재합니다.", HttpStatus.BAD_REQUEST),
    // 403
    NOT_LOGIN("로그인 후 이용할 수 있습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_CREATE("생성 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_DELETE("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_UPDATE("수정 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // 404
    NOT_FOUND_MEMBER("회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ACCOUNT("존재하지 않는 계좌번호입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_TARGET_ACCOUNT("상대 계좌가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_TRADE_NUMBER("존재하지 않는 거래번호입니다.", HttpStatus.NOT_FOUND),

    // 409
    ALREADY_JOINED("이미 존재하는 회원입니다.", HttpStatus.CONFLICT),

    //500
    FAIL_UPLOAD_S3("AWS S3 업로드 실패!", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}