package com.kcc.banking.domain.customer.controller;

import com.kcc.banking.domain.customer.dto.response.CustomerSearchDTO;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import com.kcc.banking.domain.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/api/employee/customer")
    public List<CustomerSearchInfo> searchCustomers(@RequestParam(required = false) String customerName,
                                                    @RequestParam(required = false) String customerNumber,
                                                    @RequestParam(required = false) String customerPhone) {

        CustomerSearchDTO searchDTO = new CustomerSearchDTO();
        searchDTO.setCustomerName(customerName);
        searchDTO.setCustomerNumber(customerNumber);
        searchDTO.setCustomerPhone(customerPhone);

        // 고객 정보 검색
        List<CustomerSearchInfo> customers = customerService.findCustomers(searchDTO);


        return customers;
    }
}
