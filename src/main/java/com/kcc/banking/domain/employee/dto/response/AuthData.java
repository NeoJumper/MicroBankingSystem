package com.kcc.banking.domain.employee.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthData {
    private String id;
    private String name;
    private String roles;
    private String branchId;
    private String branchName;

    @Builder
    public AuthData(String id, String name, String roles,String branchId, String branchName) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.branchId = branchId;
        this.branchName = branchName;
    }

}
