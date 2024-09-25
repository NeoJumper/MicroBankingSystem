package com.kcc.banking.domain.employee.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class EmployeeDataOfList {

    private int id;
    private String name;
    private Timestamp birthDate;
    private String phoneNumber;
    private String email;
    private String branchName;
    private String roles;


    @Builder
    public EmployeeDataOfList(int id, String name, Timestamp birthDate, String phoneNumber, String email, String branchName, String roles) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.branchName = branchName;
        this.roles = roles;
    }
}
