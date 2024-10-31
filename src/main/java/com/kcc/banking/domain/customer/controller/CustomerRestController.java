package com.kcc.banking.domain.customer.controller;

import com.kcc.banking.domain.customer.dto.request.CustomerCreate;
import com.kcc.banking.domain.customer.dto.request.CustomerUpdate;
import com.kcc.banking.domain.customer.dto.response.CreatedCustomer;
import com.kcc.banking.domain.customer.dto.request.CustomerSearch;
import com.kcc.banking.domain.customer.dto.response.CustomerDetail;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchResult;
import com.kcc.banking.domain.customer.dto.response.UpdatedCustomer;
import com.kcc.banking.domain.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/api/common/customer")
    public CustomerSearchResult searchCustomers(@ModelAttribute CustomerSearch customerSearch) {

        // 고객 정보 검색
        CustomerSearchResult customers = customerService.findCustomers(customerSearch);

        return customers;
    }
    @GetMapping("/api/common/customer/{customerId}")
    public CustomerDetail searchCustomers(@PathVariable Long customerId) {

        // 고객 정보 검색
        CustomerDetail customer = customerService.findCustomer(customerId);

        return customer;
    }

    @PostMapping("/api/common/customer")
    public CreatedCustomer createCustomer(@RequestBody CustomerCreate customerCreate) {
        return customerService.createCustomer(customerCreate);

    }

    @PutMapping("/api/common/customer")
    public UpdatedCustomer updateCustomer(@RequestBody CustomerUpdate customerUpdate) {
        return customerService.updateCustomer(customerUpdate);

    }
}
