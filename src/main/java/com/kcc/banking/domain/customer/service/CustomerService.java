package com.kcc.banking.domain.customer.service;

import com.kcc.banking.domain.customer.dto.response.CustomerSearchDTO;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import com.kcc.banking.domain.customer.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final  CustomerMapper customerMapper;

    public List<CustomerSearchInfo> findCustomers(CustomerSearchDTO customerSearchDTO){
        return customerMapper.findCustomers(customerSearchDTO);
    }
}
