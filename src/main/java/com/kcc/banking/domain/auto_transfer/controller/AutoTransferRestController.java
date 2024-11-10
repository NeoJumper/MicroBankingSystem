package com.kcc.banking.domain.auto_transfer.controller;


import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.auto_transfer.dto.request.AutoTransferCreate;
import com.kcc.banking.domain.auto_transfer.service.AutoTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AutoTransferRestController {

    private AutoTransferService autoTransferService;

    //자동이체 하기
    @PostMapping("/api/employee/auto-transfer/create")
    public int createAutoTransferInfo(@RequestBody AutoTransferCreate autoTransferCreate){
        System.out.println(autoTransferCreate.getAutoTransferDate());
        return autoTransferService.createAutoTransferInfo(autoTransferCreate);
    }




}
