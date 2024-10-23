package com.kcc.banking.domain.customer.dto.request;

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
    private String email;
    private String registrantId;

    @Builder
    public CustomerCreate(Long id, Long branchId, String birthDate, String name, String phoneNumber, String email, String roles, String registrantId, String registrationDate, Long version) {
        this.id = id;
        this.branchId = branchId;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registrantId = registrantId;
    }
}
