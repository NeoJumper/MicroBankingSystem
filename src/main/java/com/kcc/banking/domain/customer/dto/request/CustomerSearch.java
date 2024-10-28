package com.kcc.banking.domain.customer.dto.request;

import com.kcc.banking.domain.trade.dto.response.Criteria;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerSearch {
    private String searchOption;
    private String searchValue;
    private Criteria criteria;

    @Builder
    public CustomerSearch(String searchOption, String searchValue, Criteria criteria) {
        this.searchOption = searchOption;
        this.searchValue = searchValue;
        this.criteria = criteria;
    }
}
