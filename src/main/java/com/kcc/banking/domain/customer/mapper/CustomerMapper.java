package com.kcc.banking.domain.customer.mapper;

import com.kcc.banking.domain.customer.dto.request.CustomerCreate;
import com.kcc.banking.domain.customer.dto.request.CustomerSearch;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<CustomerSearchInfo> findCustomers(CustomerSearch searchDTO);

    void insertCustomer(CustomerCreate customerCreate);

    int getCustomerCount(CustomerSearch customerSearch);
}
