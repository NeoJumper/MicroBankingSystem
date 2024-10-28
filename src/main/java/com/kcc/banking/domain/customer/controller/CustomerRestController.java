package com.kcc.banking.domain.customer.controller;

import com.kcc.banking.domain.customer.dto.request.CustomerCreate;
import com.kcc.banking.domain.customer.dto.response.CreatedCustomer;
import com.kcc.banking.domain.customer.dto.request.CustomerSearch;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchResult;
import com.kcc.banking.domain.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/api/employee/customer")
    public CustomerSearchResult searchCustomers(@ModelAttribute CustomerSearch customerSearch) {

        // 고객 정보 검색
        CustomerSearchResult customers = customerService.findCustomers(customerSearch);

        return customers;
    }
    @PostMapping("/api/employee/customer")
    public CreatedCustomer createEmployee(@RequestBody CustomerCreate customerCreate) {
        return customerService.createCustomer(customerCreate);

    }
}
