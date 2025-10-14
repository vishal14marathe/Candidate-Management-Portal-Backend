package com.project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
    private Long id;
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;
    
    @NotBlank(message = "Qualification is required")
    private String qualification;
    
    @NotBlank(message = "Identity proof number is required")
    private String identityProofNumber;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    
    @NotBlank(message = "Occupation status is required")
    private String occupationStatus;
    
    private String resumePath;
    
    private LocalDateTime createdAt;
}
