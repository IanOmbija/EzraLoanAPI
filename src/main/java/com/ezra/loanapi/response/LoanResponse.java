package com.ezra.loanapi.response;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class LoanResponse {
    private boolean status;
    private String message;
    private Object body;
    private LocalDateTime timestamp;
    
}
