package com.ezra.loanapi.controller;


import com.ezra.loanapi.service.LoanService;
import com.ezra.loanapi.dto.LoanDTO;
import com.ezra.loanapi.dto.LoanRepaymentRequestDTO;
import com.ezra.loanapi.dto.SubscriberDTO;
import com.ezra.loanapi.model.Loans;
import com.ezra.loanapi.response.LoanResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<LoanResponse> lend(@RequestBody Loans loanDTO){
        LoanResponse loanResponse = loanService.lend(loanDTO);
        return new ResponseEntity<>(loanResponse, HttpStatus.CREATED);

    }

    @PutMapping("/repay/{loanReferenceNumber}")
    public ResponseEntity<LoanResponse>repayLoan(@PathVariable("loanReferenceNumber") long loanReferenceNumber, @RequestBody LoanRepaymentRequestDTO loanRepaymentRequestDTO){
        LoanResponse loanResponse = loanService.repayLoan(loanReferenceNumber, loanRepaymentRequestDTO);
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);


    }

}
