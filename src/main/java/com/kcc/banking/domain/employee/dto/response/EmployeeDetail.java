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
    private String registrantName;
    private String birthDate;
    private String name;
    private String phoneNumber;
    private String address;
    private String detailAddress;
    private String identificationCode;
    private String registrationDate;
    private String email;
    private String password;
    private String roles;

    @Builder
    public EmployeeDetail(Long id, String branchName, String registrantName,String registrationDate,String birthDate, String name, String phoneNumber, String address, String detailAddress, String identificationCode, String email, String password, String roles) {
        this.id = id;
        this.branchName = branchName;
        this.registrantName = registrantName;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = registrationDate;
        this.detailAddress = detailAddress;
        this.identificationCode = identificationCode;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
