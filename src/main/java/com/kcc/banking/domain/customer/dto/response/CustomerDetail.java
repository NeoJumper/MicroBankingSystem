package com.kcc.banking.domain.customer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class CustomerDetail {


    private Long id;
    private String name;
    private String birthDate;
    private String identificationCode;
    private String phoneNumber;
    private String email;
    private String address;
    private String detailAddress;
    private String gender;
    private String otpKey;

    @Builder
    public CustomerDetail(Long id, String name, String birthDate, String identificationCode, String phoneNumber, String email, String address, String detailAddress, String gender, String otpKey) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.identificationCode = identificationCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.detailAddress = detailAddress;
        this.gender = gender;
        this.otpKey = otpKey;
    }
}
