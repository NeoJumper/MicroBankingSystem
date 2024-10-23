package com.kcc.banking.domain.customer.mapper;

import com.kcc.banking.domain.customer.dto.request.CustomerCreate;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchDTO;
import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<CustomerSearchInfo> findCustomers(CustomerSearchDTO searchDTO);

    void insertCustomer(CustomerCreate customerCreate);

}
