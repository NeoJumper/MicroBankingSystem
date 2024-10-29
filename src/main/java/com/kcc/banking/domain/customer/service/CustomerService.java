package com.kcc.banking.domain.customer.service;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.customer.dto.request.CustomerCreate;
import com.kcc.banking.domain.customer.dto.request.CustomerUpdate;
import com.kcc.banking.domain.customer.dto.response.*;
import com.kcc.banking.domain.customer.dto.request.CustomerSearch;
import com.kcc.banking.domain.customer.mapper.CustomerMapper;
import com.kcc.banking.domain.trade.dto.response.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CommonService commonService;


    public CustomerSearchResult findCustomers(CustomerSearch customerSearch){

        int totalCount = customerMapper.getCustomerCount(customerSearch);

        // 페이징 처리 객체 생성
        PageDTO pageDTO = new PageDTO(customerSearch.getCriteria(), totalCount);
        List<CustomerSearchInfo> customers = customerMapper.findCustomers(customerSearch);

        return CustomerSearchResult.of(customers, pageDTO);
    }
    public CreatedCustomer createCustomer(CustomerCreate customerCreate){
        CurrentData currentData = commonService.getCurrentData();
        customerCreate.setCommonData(currentData);

        customerMapper.insertCustomer(customerCreate);

        return CreatedCustomer.of(customerCreate, currentData);
    }

    public CustomerDetail findCustomer(Long customerId) {
        return customerMapper.findCustomer(customerId);
    }

    public UpdatedCustomer updateCustomer(CustomerUpdate customerUpdate) {
        CurrentData currentData = commonService.getCurrentData();
        customerUpdate.setCommonData(currentData);

        customerMapper.updateCustomer(customerUpdate);

        return UpdatedCustomer.of(customerUpdate, currentData);
    }
}
