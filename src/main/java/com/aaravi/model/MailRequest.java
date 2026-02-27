package com.aaravi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

    @NotBlank(message = "Service selection is required")
    private String service;

    @NotBlank(message = "Sub-Service selection is required")
    private String subService;

    @NotBlank(message = "Message is required")
    private String message;
}
