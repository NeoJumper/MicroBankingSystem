package com.kcc.banking.common.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 400
    EMPTY_REFRESH_TOKEN("RefreshToken이 필요합니다.", HttpStatus.BAD_REQUEST),
    EMPTY_EMAIL("이메일이 필요합니다.", HttpStatus.BAD_REQUEST),
    INVALID_CODE("not invalid Code",HttpStatus.BAD_REQUEST ),

    // 403
    NOT_LOGIN("로그인 후 이용할 수 있습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_CREATE("생성 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_DELETE("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_UPDATE("수정 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // 404
    NOT_FOUND_MEMBER("회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND),

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