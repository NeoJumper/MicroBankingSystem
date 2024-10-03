package com.kcc.banking.domain.employee.dto.request;

import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCreate {
    private Long id;
    private Long branchId;
    private String birthDate;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String roles;
    private String registrantId;
    private String registrationDate;
    private Long version;


    @Builder
    public EmployeeCreate(Long id, Long branchId, String birthDate, String name, String phoneNumber, String email, String password, String roles, String registrantId, String registrationDate, Long version) {
        this.id = id;
        this.branchId = branchId;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.registrantId = registrantId;
        this.registrationDate = registrationDate;
        this.version = version;
    }

    public void  setCommonColumn(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId){
        this.registrantId = String.valueOf(currentBusinessDateAndEmployeeId.getEmployeeId());
        this.registrationDate = currentBusinessDateAndEmployeeId.getBusinessDate();
        this.version = 1L;
    }
}
