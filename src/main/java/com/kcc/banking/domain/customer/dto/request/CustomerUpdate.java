package com.kcc.banking.domain.customer.dto.request;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class CustomerUpdate {

    private Long id;
    private Timestamp birthDate;
    private String name;
    private String phoneNumber;
    private String address;
    private String gender;
    private String detailAddress;
    private String identificationCode;
    private String email;
    private String modifierId;

    @Builder
    public CustomerUpdate(Long id, Timestamp birthDate, String name, String phoneNumber, String address, String gender, String detailAddress, String identificationCode, String email, String modifierId) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.detailAddress = detailAddress;
        this.identificationCode = identificationCode;
        this.email = email;
        this.modifierId = modifierId;
    }

    public void setCommonData(CurrentData currentData) {
        this.modifierId = String.valueOf(currentData.getEmployeeId());
    }
}
