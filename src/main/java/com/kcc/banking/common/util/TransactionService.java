package com.kcc.banking.common.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {


    @Async
    @Transactional
    public void invokeAsync(TransactionFunction<Void> transactionFunction) {
        transactionFunction.apply();
    }
}