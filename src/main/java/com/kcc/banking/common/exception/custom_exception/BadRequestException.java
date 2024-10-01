package com.kcc.banking.common.exception.custom_exception;

import com.kcc.banking.common.exception.CustomException;
import com.kcc.banking.common.exception.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}