package com.ezra.loanapi.service;

import com.ezra.loanapi.controller.LoanController;
import com.ezra.loanapi.dto.LoanDTO;
import com.ezra.loanapi.dto.LoanRepaymentRequestDTO;
import com.ezra.loanapi.dto.SubscriberDTO;
import com.ezra.loanapi.model.*;
import com.ezra.loanapi.repository.LoanRepository;
import com.ezra.loanapi.repository.SubscriberRepository;
import com.ezra.loanapi.exception.LoanRepaymentException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class LoanServiceTest {

    @Autowired
    private LoanController loanController;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @BeforeEach

    public void setup(){
        //clear the db
        loanRepository.deleteAll();
        subscriberRepository.deleteAll();
        
    }

    @Test
    public void testLend(){
        //Create subscriber

        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("Ian");
        subscriber.setLastName("Doe");
        subscriber.setMsisdn("712345678");
        subscriber = subscriberRepository.save(subscriber);

        //Lend the money to the subscriber
        Loans loan = new Loans();
        loan.setSubcriberMsisdn("712345678");
        loan.setAmount(BigDecimal.valueOf(200));
        loan.setRepayAmount(BigDecimal.valueOf(200));
        loan.setDueDate("2023-04-12");

        //verify that the loan was saved in the db
        List<Loans> loans = loanRepository.findBySubscriber(subscriber);
        assertEquals(1, loans.size());
        assertEquals(BigDecimal.valueOf(200), loans.get(1).getAmount());
    }

    @Test
    public void testRepay(){
        //Create subscriber

        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("Ian");
        subscriber.setLastName("Doe");
        subscriber.setMsisdn("712345678");
        subscriber = subscriberRepository.save(subscriber);

        //Lend the money to the subscriber
        Loans loan = new Loans();
        loan.setSubcriberMsisdn("712345678");
        loan.setAmount(BigDecimal.valueOf(200));
        loan.setRepayAmount(BigDecimal.valueOf(200));
        loan.setDueDate("2023-04-12");

        //Repay the loan
        //loanController.repayLoan(loan.getId());

        //verify that the loan was updated in the db
        List<Loans> loans = loanRepository.findBySubscriber(subscriber);
        assertEquals(1, loans.size());
        assertEquals(BigDecimal.valueOf(200), loans.get(1).getAmount());


    }
    
}
