package com.aaravi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MailRequest {

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @NotBlank(message = "Company Name is required")
    private String companyName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Service selection is required")
    private String service;

    @NotBlank(message = "Sub-Service selection is required")
    private String subService;

    @NotBlank(message = "Message is required")
    private String message;

    public String getFullName() {
        return fullName;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getService() {
        return service;
    }
    
    public String getSubService() {
        return subService;
    }
    
    public String getMessage() {
        return message;
    }
    
}