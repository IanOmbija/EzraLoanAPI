package com.ezra.loanapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezra.loanapi.model.Loans;
import com.ezra.loanapi.model.Subscriber;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {
    List<Loans> findBySubscriber(Subscriber subscriber);

    Loans findById(String loanReferenceNumber);

    //To delete all the defaulted loans
    List<Loans> findByLoanStatus(LocalDateTime threshold);
    
}
