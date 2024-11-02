package com.kcc.banking.common.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {
    @Transactional
    public <T> T invoke(TransactionFunction<T> transactionFunction) {
        return transactionFunction.apply();
    }
}