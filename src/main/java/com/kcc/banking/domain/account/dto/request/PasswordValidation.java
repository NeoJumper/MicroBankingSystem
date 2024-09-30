package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PasswordValidation {

    private String accountNumber;
    private String password;

    @Builder
    public PasswordValidation(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }
}
