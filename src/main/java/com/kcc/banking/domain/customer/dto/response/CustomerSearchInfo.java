package com.kcc.banking.domain.customer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@Getter
public class CustomerSearchInfo {


    private int customerId;
    private String customerName;
    private Date birthDate;
    private String phoneNumber;
    private int branchId;



    public String getFormattedBirthDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(birthDate);  // YYYY-MM-DD 형식으로 반환
    }

    @Builder
    public CustomerSearchInfo(int customerId, String customerName, Date birthDate, String phoneNumber, int branchId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.branchId = branchId;
    }
}
