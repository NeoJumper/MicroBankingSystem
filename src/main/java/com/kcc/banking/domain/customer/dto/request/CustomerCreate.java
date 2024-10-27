package com.kcc.banking.domain.customer.dto.request;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CustomerCreate {

    private Long id;
    private Long branchId;
    private String birthDate;
    private String name;
    private String phoneNumber;
    private String address;
    private String gender;
    private String detailAddress;
    private String identificationCode;
    private String email;
    private String registrantId;

    @Builder
    public CustomerCreate(Long id, Long branchId, String birthDate,String gender, String name, String phoneNumber, String address, String detailAddress, String identificationCode, String email, String registrantId) {
        this.id = id;
        this.branchId = branchId;
        this.birthDate = birthDate;
        this.gender = gender;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.detailAddress = detailAddress;
        this.identificationCode = identificationCode;
        this.email = email;
        this.registrantId = registrantId;
    }

    public void setCommonData(CurrentData currentData) {
        this.branchId = currentData.getBranchId();
        this.registrantId = String.valueOf(currentData.getEmployeeId());
    }
}
