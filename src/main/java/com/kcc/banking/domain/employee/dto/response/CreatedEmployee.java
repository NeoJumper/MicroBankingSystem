package com.kcc.banking.domain.employee.dto.response;

import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CreatedEmployee {
    private Long id;
    private String branchName;
    private Timestamp birthDate;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String roles;

    @Builder
    public CreatedEmployee(EmployeeCreate employeeCreate, String branchName, String roles) {
        this.id = employeeCreate.getId();
        this.birthDate = employeeCreate.getBirthDate();
        this.branchName = branchName;
        this.name = employeeCreate.getName();
        this.phoneNumber = employeeCreate.getPhoneNumber();
        this.email = employeeCreate.getEmail();
        this.password = employeeCreate.getPassword();
        this.roles = roles;
    }
}
