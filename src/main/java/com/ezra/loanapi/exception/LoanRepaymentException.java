package com.ezra.loanapi.exception;

public class LoanRepaymentException extends RuntimeException {

    public LoanRepaymentException(){

    }

    public LoanRepaymentException(String message){
        super(message);
    }

    public LoanRepaymentException(String message, Throwable cause){
        super(message, cause);
    }

    public LoanRepaymentException(Throwable cause){
        super(cause);
    }

    public LoanRepaymentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }
    
}
