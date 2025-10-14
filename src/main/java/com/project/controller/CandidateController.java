package com.project.controller;

import com.project.dto.CandidateDTO;
import com.project.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CandidateController {
    
    @Autowired
    private CandidateService candidateService;
    
    @PostMapping("/admin/candidates")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CandidateDTO> createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        CandidateDTO created = candidateService.createCandidate(candidateDTO);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/admin/candidates")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CandidateDTO>> getAllCandidates(
            @RequestParam(required = false) String qualification,
            @RequestParam(required = false) String occupationStatus,
            @RequestParam(required = false) String location) {
        List<CandidateDTO> candidates = candidateService.getAllCandidates(qualification, occupationStatus, location);
        return ResponseEntity.ok(candidates);
    }
    
    @GetMapping("/admin/candidates/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable Long id) {
        CandidateDTO candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }
    
    @PutMapping("/candidates/{id}")
    public ResponseEntity<CandidateDTO> updateCandidate(@PathVariable Long id, @Valid @RequestBody CandidateDTO candidateDTO) {
        CandidateDTO updated = candidateService.updateCandidate(id, candidateDTO);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/admin/candidates/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok("Candidate deleted successfully");
    }
}
