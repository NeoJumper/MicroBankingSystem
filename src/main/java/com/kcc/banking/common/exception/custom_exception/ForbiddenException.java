package com.kcc.banking.common.exception.custom_exception;

import com.kcc.banking.common.exception.CustomException;
import com.kcc.banking.common.exception.ErrorCode;

public class ForbiddenException extends CustomException {
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}