package com.kcc.banking.domain.employee.dto.request;

import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdate {
    private Long id;
    private String name;
    private String birthDate;
    private String password;
    private String address;
    private String detailAddress;
    private String identificationCode;
    private String email;
    private String phoneNumber;
    private String roles;
    private Long branchId;
    private String modifierId;

    @Builder
    public EmployeeUpdate(Long id, String name, String birthDate, String password, String address, String detailAddress, String identificationCode, String email, String phoneNumber, String roles, Long branchId, String modifierId) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.password = password;
        this.address = address;
        this.detailAddress = detailAddress;
        this.identificationCode = identificationCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.branchId = branchId;
        this.modifierId = modifierId;
    }

    public void  setCommonColumn(CurrentData currentData){
        this.modifierId = String.valueOf(currentData.getEmployeeId());
        this.branchId = currentData.getBranchId();
    }
}
