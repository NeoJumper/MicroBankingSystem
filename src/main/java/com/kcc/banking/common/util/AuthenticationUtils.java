package com.kcc.banking.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationUtils {

    public static Long getLoginMemberId(){
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
