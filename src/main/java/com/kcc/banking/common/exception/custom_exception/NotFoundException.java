package com.kcc.banking.common.exception.custom_exception;


import com.kcc.banking.common.exception.CustomException;
import com.kcc.banking.common.exception.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}