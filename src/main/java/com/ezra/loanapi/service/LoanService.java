package com.ezra.loanapi.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.ezra.loanapi.dto.LoanDTO;
import com.ezra.loanapi.dto.LoanRepaymentRequestDTO;
import com.ezra.loanapi.enums.LoanStatus;
import com.ezra.loanapi.exception.LoanRepaymentException;
import com.ezra.loanapi.model.Loans;
import com.ezra.loanapi.model.Subscriber;
import com.ezra.loanapi.response.LoanResponse;
import com.ezra.loanapi.repository.LoanRepository;
import com.ezra.loanapi.repository.SubscriberRepository;
import com.ezra.loanapi.service.SmsNotifcationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final SubscriberRepository subscriberRepository;
    private SmsNotifcationService smsNotifcationService;

    @Value("${loan.clear.age}")
    private int loanClearAge;

    @Autowired
    public LoanService(LoanRepository loanRepository, SubscriberRepository subscriberRepository, SmsNotifcationService smsNotifcationService){
        this.loanRepository = loanRepository;
        this.subscriberRepository = subscriberRepository;
        this.smsNotifcationService = smsNotifcationService;
    }

    /*
     * Clear the defaulted/pending Loans
     */
    @Scheduled(cron="0 0 0 * * *")
    public void clearDefaultedLoans(){
        LocalDateTime threshold = LocalDateTime.now().minusMonths(loanClearAge);
        List<Loans> defaultedLoans = loanRepository.findByLoanStatus(threshold);
        loanRepository.deleteAll(defaultedLoans);

    }
    /*
     * Lending Loan to a subscriber
     */
    public LoanResponse lend(Loans loanDTO){
        Subscriber subscriber = subscriberRepository.findByMsisdn(loanDTO.getSubcriberMsisdn());
        
        if(subscriber == null){
            System.out.println("\n\n\n\n\n" + subscriber);
            throw new LoanRepaymentException("Subscriber not found" + loanDTO.getSubcriberMsisdn() +  "!");
        }

        Loans loan = new Loans();
        loan.setSubcriberMsisdn(loanDTO.getSubcriberMsisdn());
        loan.setAmount(loanDTO.getAmount());
        System.out.println("\n\n\n\n\n" + subscriber);
        loan.setSubscriber(loanDTO.getSubscriber());
        loan.setDueDate(loanDTO.getDueDate());
        loan.setRepayAmount(loanDTO.getRepayAmount());
        loan.setLoanDate(LocalDate.now());
        loan.setLoanStatus(LoanStatus.APPROVED);
        Loans savedloan = loanRepository.save(loan);

        //Send SMS Notification
        /*
         * Commented out the SMS service out because we will get Authentication Error
         * Substitute with the correct values at the application.prop - inorder for test
         */
        // 
        
        //smsNotifcationService.sendLoanTakenNotification(loanDTO.getSubcriberMsisdn(), loanDTO.getAmount());
        
        return LoanResponse.builder()
            .status(true)
            .timestamp(LocalDateTime.now())
            .message("Loan of amount " + loan.getAmount() + " Disbursed")
            .body(loan)
            .build();
        

    }

    /*
     * Repayment of Loan borrowed by Subscriber
     */

     public LoanResponse repayLoan(long loanReferenceNumber, LoanRepaymentRequestDTO loanRepaymentRequestDTO){
        Optional<Loans> loansOptional = loanRepository.findById(loanReferenceNumber);
        //Loans loan = loanRepository.findById(loanRepaymentRequestDTO.getLoanReferenceNumber());
        if (loansOptional == null){
            throw new LoanRepaymentException("Loan not found with the ID: " + loanRepaymentRequestDTO.getLoanReferenceNumber());
        }
        //Check the amount repaid
        Loans loan = loansOptional.get();
        BigDecimal amountToBeRepaid = loan.getAmount().subtract(loan.getRepayAmount());
        BigDecimal repaidAmount = loanRepaymentRequestDTO.getRepaymentAmount();
        if(repaidAmount.compareTo(amountToBeRepaid) >= 0){
            loan.setLoanStatus(LoanStatus.REPAID);
            loan.setRepayAmount(loan.getAmount());
        }else{
            loan.setRepayAmount(loan.getRepayAmount().add(repaidAmount));
        }

        Loans savedLoan = loanRepository.save(loan);
        /*
        * Send SMS Notification - commented out because of test purposes
        */
        
        //smsNotifcationService.sendRepaymentNotification(loan.getSubscriber().getMsisdn(), repaidAmount);
        return LoanResponse.builder()
            .status(true)
            .timestamp(LocalDateTime.now())
            .message("Loan repayment of amount: " + repaidAmount + " is succesfully done.")
            .body(savedLoan)
            .build();
     }
    
}
