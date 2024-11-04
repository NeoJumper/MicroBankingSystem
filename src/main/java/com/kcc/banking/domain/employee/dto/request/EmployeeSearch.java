package com.kcc.banking.domain.employee.dto.request;

import com.kcc.banking.domain.trade.dto.response.Criteria;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EmployeeSearch {

    private String searchOption;
    private String searchValue;
    private Criteria criteria;
    private String branchId;

    @Builder
    public EmployeeSearch(String searchOption, String searchValue, Criteria criteria, String branchId) {
        this.searchOption = searchOption;
        this.searchValue = searchValue;
        this.criteria = criteria;
        this.branchId = branchId;
    }
}
