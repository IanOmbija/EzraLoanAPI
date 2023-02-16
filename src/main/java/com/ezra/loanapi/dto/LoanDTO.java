package com.ezra.loanapi.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

import com.ezra.loanapi.enums.LoanStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private Long id;
    private String subcriberMsisdn;
    private BigDecimal amount;
    private Date loanDate;
    private Date dueDate;
    private BigDecimal repayAmount;
    private LoanStatus loanStatus;
    
}
