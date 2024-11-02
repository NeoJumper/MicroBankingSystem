package com.kcc.banking.common.util;

import com.kcc.banking.domain.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class Transaction {

    private final TransactionService transactionService;
    private final CommonService commonService;

    @Async
    public CompletableFuture<Void> invokeAsync(TransactionFunction<Void> transactionFunction) {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        return CompletableFuture.runAsync(() -> {
            // 별도의 서비스 클래스를 통해 트랜잭션 처리
            SecurityContextHolder.setContext(securityContext);
            transactionService.invoke(transactionFunction);
        });
    }
}