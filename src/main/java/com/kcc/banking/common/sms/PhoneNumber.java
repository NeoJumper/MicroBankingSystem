package com.kcc.banking.common.sms;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PhoneNumber {

    private String number;
    private String certificationNumber;

    @Builder
    public PhoneNumber(String number, String certificationNumber) {
        this.number = number;
        this.certificationNumber = certificationNumber;
    }
}
