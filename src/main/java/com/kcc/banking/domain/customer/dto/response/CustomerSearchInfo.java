package com.kcc.banking.domain.customer.dto.response;

import com.kcc.banking.domain.trade.dto.response.PageDTO;
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
    private String securityLevel;
    private int branchId;


    @Builder
    public CustomerSearchInfo(int customerId, String customerName, Date birthDate, String phoneNumber, String securityLevel, int branchId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.securityLevel = securityLevel;
        this.branchId = branchId;
    }
}
