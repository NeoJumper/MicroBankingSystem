package com.kcc.banking.domain.employee.dto.request;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCreate {
    private Long id;
    private Long branchId;
    private Timestamp birthDate;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String detailAddress;
    private String identificationCode;
    private String password;
    private String roles;
    private String registrantId;
    private Timestamp registrationDate;
    private Long version;

    @Builder
    public EmployeeCreate(Long id, Long branchId, Timestamp birthDate, String name, String phoneNumber, String email, String address, String detailAddress, String identificationCode, String password, String roles, String registrantId, Timestamp registrationDate, Long version) {
        this.id = id;
        this.branchId = branchId;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.detailAddress = detailAddress;
        this.identificationCode = identificationCode;
        this.password = password;
        this.roles = roles;
        this.registrantId = registrantId;
        this.registrationDate = registrationDate;
        this.version = version;
    }

    public void  setCommonColumn(CurrentData currentData){
        this.registrantId = String.valueOf(currentData.getEmployeeId());
        this.registrationDate = currentData.getCurrentBusinessDate();
        this.branchId = currentData.getBranchId();
        this.version = 1L;
    }
}
