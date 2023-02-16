package com.ezra.loanapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezra.loanapi.enums.LoanStatus;

import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loans")

public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "subcriber_id")
    private Subscriber subscriber;

    @Column(name = "amount")
    private BigDecimal amount;
    private String subcriberMsisdn;
    private LocalDate loanDate;
    private String dueDate;
    private BigDecimal repayAmount;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

}


