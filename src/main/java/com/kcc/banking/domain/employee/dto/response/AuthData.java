package com.kcc.banking.domain.employee.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthData {
    private String name;
    private String roles;
    private String branchName;

    @Builder
    public AuthData(String name, String roles, String branchName) {
        this.name = name;
        this.roles = roles;
        this.branchName = branchName;
    }

}
