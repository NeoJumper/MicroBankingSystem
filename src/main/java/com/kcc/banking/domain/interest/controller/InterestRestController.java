package com.kcc.banking.domain.interest.controller;

import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import com.kcc.banking.domain.interest.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterestRestController {

    private final InterestService interestService;

    @GetMapping("/api/employee/interest-details/{accountId}")
    public ResponseEntity<List<InterestDetails>> getInterestDetailsByAccountId(@PathVariable String accountId){
        List<InterestDetails> interestDetailsList = interestService.getInterestDetailsByAccountId(accountId);
        return ResponseEntity.ok(interestDetailsList);
    }

}
