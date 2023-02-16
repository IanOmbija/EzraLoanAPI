package com.ezra.loanapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ezra.loanapi.repository.LoanRepository;
import com.ezra.loanapi.model.Loans;

import io.jsonwebtoken.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class SFTPStorageService {
    private final LoanRepository loanRepository;
    private final JdbcTemplate jdbcTemplate;
    //private final SftpRemoteFileTemplate sftpRemoteFileTemplate;

    @Autowired
    public SFTPStorageService(LoanRepository loanRepository, JdbcTemplate jdbcTemplate){
        this.loanRepository = loanRepository;
        this.jdbcTemplate = jdbcTemplate;
        
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void exportData() throws IOException, java.io.IOException{
        List<Loans> loans = loanRepository.findAll();
        String data = convertDataToCsv(loans);
        String filename = "loans_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".csv";
        Path file = Files.writeString(Paths.get(filename), data);
        Files.delete(file);
    }
    

    private String convertDataToCsv(List<Loans> loans){
        StringBuilder sb = new StringBuilder();
        sb.append("id,msisdn,amount,repaid_amount,loanstatus\n");
        for (Loans loan : loans){
            sb.append(loan.getId()).append(",");
            sb.append(loan.getSubcriberMsisdn()).append(",");
            sb.append(loan.getAmount()).append(",");
            sb.append(loan.getRepayAmount()).append(",");
            sb.append(loan.getLoanStatus()).append("\n");
        }

        return sb.toString();
    }
}
