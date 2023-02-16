package com.ezra.loanapi.service;
import org.springframework.stereotype.Service;

import com.twilio.*;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;

@Service
public class SmsNotifcationService {

    @Value("${twilio.account.sid}")
    private String userName;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.from.phonenumber}")
    private String fromPhoneNumber;

    
    public void sendLoanTakenNotification(String msisdn, BigDecimal amount){
        Twilio.init(userName, authToken);
        
        //Message body construct
        Message message = Message.creator(new PhoneNumber(msisdn), new PhoneNumber(fromPhoneNumber), 
        "You have receieved a loan of " + amount + "from Ezra.").create();
       
    }

    public void sendRepaymentNotification(String msisdn, BigDecimal amount){
        Twilio.init(userName, authToken);
        
        //Msg body construct
        Message message = Message.creator(new PhoneNumber(msisdn), new PhoneNumber(fromPhoneNumber), 
        "You have made a repayment of " + amount + " to Ezra Loan.").create();


    }
}
