package com.ezra.loanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberDTO {

    private Long id;
    private String msisdn;
    private String firstName;
    private String lastName;
    private List<LoanDTO> loans;
    
}
