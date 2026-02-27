package com.aaravi.controller;

import com.aaravi.model.MailRequest;
import com.aaravi.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
@CrossOrigin(origins = "*") // allow React frontend
public class MailController {

    private final MailService emailService;

    public MailController(MailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<MailRequest> submitContactForm(
            @Valid @RequestBody MailRequest request) throws MessagingException {

        // Send emails
        emailService.sendAdminEmail(request);
        emailService.sendAutoReply(request);

        // Return same data as response
        return ResponseEntity.ok(request);
    }
}

