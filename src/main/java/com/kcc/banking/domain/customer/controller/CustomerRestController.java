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

        System.out.println("customerName"+customerName);
        System.out.println("customerNumber"+customerNumber);
        System.out.println("customerPhone"+customerPhone);
        CustomerSearchDTO searchDTO = new CustomerSearchDTO();
        searchDTO.setCustomerName(customerName);
        searchDTO.setCustomerNumber(customerNumber);
        searchDTO.setCustomerPhone(customerPhone);

        // 고객 정보 검색
        List<CustomerSearchInfo> customers = customerService.findCustomers(searchDTO);

        for (CustomerSearchInfo  customer : customers) {
            System.out.println(customer.getBirthDate());
            System.out.println(customer.getCustomerName());
        }

        // DB에서 검색된 값 출력
        System.out.println("Retrieved customers: " + customers);


        return customers;
    }
}
