package com.ezra.loanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRepaymentRequestDTO {

    private String loanReferenceNumber;
    private String subscriberMsisdn;
    private BigDecimal repaymentAmount;
    
}
