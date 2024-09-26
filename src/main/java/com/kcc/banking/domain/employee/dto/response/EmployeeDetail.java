package com.kcc.banking.domain.employee.dto.response;

import com.kcc.banking.domain.employee.dto.request.EmployeeUpdate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDetail {
    private Long id;
    private String branchName;
    private String birthDate;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String roles;

    @Builder
    public EmployeeDetail(EmployeeUpdate employeeUpdate, String branchName, String roles) {
        this.id = employeeUpdate.getId();
        this.birthDate = employeeUpdate.getBirthDate();
        this.branchName = branchName;
        this.name = employeeUpdate.getName();
        this.phoneNumber = employeeUpdate.getPhoneNumber();
        this.email = employeeUpdate.getEmail();
        this.password = employeeUpdate.getPassword();
        this.roles = roles;
    }
}
