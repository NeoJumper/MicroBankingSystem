package com.kcc.banking.domain.customer.dto.response;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.customer.dto.request.CustomerCreate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatedCustomer {


    private Long id;
    private String branchName;
    private String registrantName;
    private String currentBusinessDate;
    private String birthDate;
    private String name;
    private String phoneNumber;
    private String address;
    private String gender;
    private String detailAddress;
    private String identificationCode;
    private String email;
    private String securityLevel;

    @Builder
    public CreatedCustomer(Long id, String branchName, String registrantName, String currentBusinessDate, String birthDate, String name, String phoneNumber, String address, String gender, String detailAddress, String identificationCode, String email, String securityLevel) {
        this.id = id;
        this.branchName = branchName;
        this.registrantName = registrantName;
        this.currentBusinessDate = currentBusinessDate;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.detailAddress = detailAddress;
        this.identificationCode = identificationCode;
        this.email = email;
        this.securityLevel = securityLevel;
    }






    public static CreatedCustomer of(CustomerCreate customerCreate, CurrentData currentData) {
        CreatedCustomer createdCustomer = CreatedCustomer.builder()
                .id(customerCreate.getId())
                .branchName(currentData.getBranchName())
                .registrantName(currentData.getEmployeeName())
                .currentBusinessDate(currentData.getCurrentBusinessDate())
                .birthDate(customerCreate.getBirthDate())
                .name(customerCreate.getName())
                .phoneNumber(customerCreate.getPhoneNumber())
                .address(customerCreate.getAddress())
                .gender(customerCreate.getGender())
                .detailAddress(customerCreate.getDetailAddress())
                .identificationCode(customerCreate.getIdentificationCode())
                .email(customerCreate.getEmail())
                .securityLevel("2등급")
                .build();

        return createdCustomer;
    }
}
