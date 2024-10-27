package com.kcc.banking.domain.customer.controller;

import com.kcc.banking.domain.customer.dto.request.CustomerCreate;
import com.kcc.banking.domain.customer.dto.response.CreatedCustomer;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchDTO;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import com.kcc.banking.domain.customer.service.CustomerService;
import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.response.CreatedEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/api/employee/customer")
    public List<CustomerSearchInfo> searchCustomers(@RequestParam(required = false) String customerId,
                                                    @RequestParam(required = false) String customerName,
                                                    @RequestParam(required = false) String customerPhone) {

        CustomerSearchDTO searchDTO = new CustomerSearchDTO();
        searchDTO.setCustomerName(customerName);
        searchDTO.setCustomerId( customerId);
        searchDTO.setCustomerPhone(customerPhone);

        // 고객 정보 검색
        List<CustomerSearchInfo> customers = customerService.findCustomers(searchDTO);


        return customers;
    }
    @PostMapping("/api/employee/customer")
    public CreatedCustomer createEmployee(@RequestBody CustomerCreate customerCreate) {
        return customerService.createCustomer(customerCreate);

    }
}
