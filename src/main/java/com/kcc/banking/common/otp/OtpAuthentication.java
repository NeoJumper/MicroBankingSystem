package com.kcc.banking.common.otp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OtpAuthentication {

    private String userCode;
    private Long userId;

    @Builder
    public OtpAuthentication(String userCode, Long userId) {
        this.userCode = userCode;
        this.userId = userId;
    }
}
