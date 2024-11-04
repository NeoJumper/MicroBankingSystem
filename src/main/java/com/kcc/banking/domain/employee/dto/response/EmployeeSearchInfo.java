package com.kcc.banking.domain.employee.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class EmployeeSearchInfo {


    private int id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String branchName;
    private String roles;


    @Builder
    public EmployeeSearchInfo(int id, String name, String email, String address, String phoneNumber, String branchName, String roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.branchName = branchName;
        this.roles = roles;
    }
}
