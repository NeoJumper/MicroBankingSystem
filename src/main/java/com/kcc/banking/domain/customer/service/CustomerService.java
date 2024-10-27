package com.kcc.banking.domain.customer.service;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.customer.dto.request.CustomerCreate;
import com.kcc.banking.domain.customer.dto.response.CreatedCustomer;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchDTO;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import com.kcc.banking.domain.customer.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CommonService commonService;


    public List<CustomerSearchInfo> findCustomers(CustomerSearchDTO customerSearchDTO){
        return customerMapper.findCustomers(customerSearchDTO);
    }
    public CreatedCustomer createCustomer(CustomerCreate customerCreate){
        CurrentData currentData = commonService.getCurrentData();
        customerCreate.setCommonData(currentData);

        customerMapper.insertCustomer(customerCreate);

        return CreatedCustomer.of(customerCreate, currentData);
    }
}
