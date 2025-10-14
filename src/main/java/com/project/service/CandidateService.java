package com.project.service;

import com.project.dto.CandidateDTO;
import com.project.model.Candidate;
import com.project.repository.CandidateRepository;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public CandidateDTO createCandidate(CandidateDTO candidateDTO) {
        if (candidateRepository.existsByEmail(candidateDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (candidateRepository.existsByIdentityProofNumber(candidateDTO.getIdentityProofNumber())) {
            throw new RuntimeException("Identity proof number already exists");
        }
        
        Candidate candidate = modelMapper.map(candidateDTO, Candidate.class);
        Candidate savedCandidate = candidateRepository.save(candidate);
        return modelMapper.map(savedCandidate, CandidateDTO.class);
    }
    
    public List<CandidateDTO> getAllCandidates(String qualification, String occupationStatus, String location) {
        Specification<Candidate> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (qualification != null && !qualification.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("qualification"), qualification));
            }
            if (occupationStatus != null && !occupationStatus.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("occupationStatus"), occupationStatus));
            }
            if (location != null && !location.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("location"), location));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        
        List<Candidate> candidates = candidateRepository.findAll(spec);
        return candidates.stream()
                .map(candidate -> modelMapper.map(candidate, CandidateDTO.class))
                .collect(Collectors.toList());
    }
    
    public CandidateDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));
        return modelMapper.map(candidate, CandidateDTO.class);
    }
    
    public CandidateDTO updateCandidate(Long id, CandidateDTO candidateDTO) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));
        
        if (!candidate.getEmail().equals(candidateDTO.getEmail()) && 
            candidateRepository.existsByEmail(candidateDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        candidate.setFullName(candidateDTO.getFullName());
        candidate.setAge(candidateDTO.getAge());
        candidate.setQualification(candidateDTO.getQualification());
        candidate.setLocation(candidateDTO.getLocation());
        candidate.setEmail(candidateDTO.getEmail());
        candidate.setMobileNumber(candidateDTO.getMobileNumber());
        candidate.setOccupationStatus(candidateDTO.getOccupationStatus());
        
        Candidate updatedCandidate = candidateRepository.save(candidate);
        return modelMapper.map(updatedCandidate, CandidateDTO.class);
    }
    
    public void deleteCandidate(Long id) {
        if (!candidateRepository.existsById(id)) {
            throw new RuntimeException("Candidate not found with id: " + id);
        }
        candidateRepository.deleteById(id);
    }
}
