package com.kcc.banking.domain.branch.controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BranchController {

    @GetMapping("/page/manager/cash-exchange")
    public String cashExchange() {
        return "branch/cash-exchange";
    }

}

