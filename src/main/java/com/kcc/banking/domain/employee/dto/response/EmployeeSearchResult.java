package com.kcc.banking.domain.employee.dto.response;

import com.kcc.banking.domain.customer.dto.response.CustomerSearchInfo;
import com.kcc.banking.domain.trade.dto.response.PageDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeSearchResult {

    private List<EmployeeSearchInfo> employeeSearchInfoList;
    private PageDTO pageDTO;

    @Builder
    public EmployeeSearchResult(List<EmployeeSearchInfo> employeeSearchInfoList, PageDTO pageDTO) {
        this.employeeSearchInfoList = employeeSearchInfoList;
        this.pageDTO = pageDTO;
    }

    public static EmployeeSearchResult of(List<EmployeeSearchInfo> employeeSearchInfoList, PageDTO pageDTO){
        return EmployeeSearchResult.builder()
                .employeeSearchInfoList(employeeSearchInfoList)
                .pageDTO(pageDTO)
                .build();
    }
}
