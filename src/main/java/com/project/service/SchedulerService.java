package com.project.service;

import com.project.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SchedulerService {
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Scheduled(cron = "0 0 21 * * ?")
    public void sendDailySummaryToAdmin() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        
        long count = candidateRepository.countByCreatedAtBetween(yesterday, now);
        emailService.sendAdminSummary(count);
        
        System.out.println("Daily summary executed: " + count + " candidates added in last 24 hours");
    }
}
