package com.kcc.banking.domain.common.controller;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommonRestController {

    private final CommonService commonService;

    @GetMapping("/api/common/current-data")
    public CurrentData getCurrentData() {
        return commonService.getCurrentData();
    }

}
