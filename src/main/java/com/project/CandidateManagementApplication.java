package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.context.SecurityContextHolder;
=======
>>>>>>> 2c8a9bc337fb232742624c6aa4705a15a0573a1a

@SpringBootApplication
public class CandidateManagementApplication {
    public static void main(String[] args) {
        // Set SecurityContextHolder strategy to ensure thread-local isolation
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
        SpringApplication.run(CandidateManagementApplication.class, args);
    }
}