package com.kcc.banking.common.util;

@FunctionalInterface
public interface TransactionFunction<T> {
    T apply();
}