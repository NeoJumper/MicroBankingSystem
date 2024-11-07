package com.kcc.banking.domain.business_day_close.controller;

import com.kcc.banking.domain.business_day_management.service.BusinessDayManagementFacadeTest;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class BusinessDayCloseControllerTest {

    private final BusinessDayManagementFacadeTest businessDayManagementFacadeTest;

    public void businessDayCloseOfManagerForTest(BusinessDateAndBranchId businessDateAndBranchId, CurrentData currentData) {
        businessDayManagementFacadeTest.closeByManagerForTest(businessDateAndBranchId, currentData);
    }
}
