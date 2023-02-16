package com.ezra.loanapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.ezra.loanapi.model.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{
    
    Subscriber findByMsisdn(String msisdn);
    
}
