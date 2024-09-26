package com.kcc.banking.domain.employee.dto.request;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class EmployeeSearch {

    private String searchOption;
    private String searchValue;

    @Builder
    public EmployeeSearch(String searchOption, String searchValue) {
        this.searchOption = searchOption;
        this.searchValue = searchValue;
    }
}
