package com.kcc.banking.domain.auto_transfer.controller;


import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.auto_transfer.dto.request.AutoTransferCreate;
import com.kcc.banking.domain.auto_transfer.dto.response.AutoTransferList;
import com.kcc.banking.domain.auto_transfer.service.AutoTransferService;
import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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


    @GetMapping("/api/employee/auto-transfer/auto-list")
    public List<AutoTransferList> findSelectedAutoTransferList (){
        return autoTransferService.findSelectedAutoTransferList();
    }

    @GetMapping("/api/employee/auto-transfer/reserve-list")
    public List<ReserveList> findReserveList(){
        return autoTransferService.findReserveList();
    }

}
