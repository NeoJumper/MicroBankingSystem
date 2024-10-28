package com.kcc.banking.domain.customer.dto.response;

import com.kcc.banking.domain.trade.dto.response.PageDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CustomerSearchResult {

    private List<CustomerSearchInfo> customerSearchInfoList;
    private PageDTO pageDTO;

    @Builder
    public CustomerSearchResult(List<CustomerSearchInfo> customerSearchInfoList, PageDTO pageDTO) {
        this.customerSearchInfoList = customerSearchInfoList;
        this.pageDTO = pageDTO;
    }

    public static CustomerSearchResult of(List<CustomerSearchInfo> customerSearchInfoList, PageDTO pageDTO){
        return CustomerSearchResult.builder()
                .customerSearchInfoList(customerSearchInfoList)
                .pageDTO(pageDTO)
                .build();
    }
}
