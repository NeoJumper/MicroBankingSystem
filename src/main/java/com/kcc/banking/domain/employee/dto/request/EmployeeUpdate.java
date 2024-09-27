package com.kcc.banking.domain.employee.dto.request;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdate {
    private Long id;
    private Long branchId;
    private String birthDate;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String roles;

    @Builder
    public EmployeeUpdate(Long id, String birthDate, Long branchId, String name, String phoneNumber, String email, String password, String roles) {
        this.id = id;
        this.birthDate = birthDate;
        this.branchId = branchId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
