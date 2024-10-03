package com.kcc.banking.domain.employee.dto.request;

import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdate {
    private Long id;
    private Long branchId;
    private String birthDate;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String roles;
    private String modifierId;
    private String modificationDate;
    private Long version;

    @Builder
    public EmployeeUpdate(Long id, Long branchId, String birthDate, String name, String phoneNumber, String email, String password, String roles, String modifierId, String modificationDate, Long version) {
        this.id = id;
        this.branchId = branchId;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.modifierId = modifierId;
        this.modificationDate = modificationDate;
        this.version = version;
    }

    public void  setCommonColumn(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId){
        this.modifierId = String.valueOf(currentBusinessDateAndEmployeeId.getEmployeeId());
        this.modificationDate = currentBusinessDateAndEmployeeId.getBusinessDate();
        this.version = 1L;
    }
}
