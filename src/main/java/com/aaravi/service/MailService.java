package com.aaravi.service;

import com.aaravi.model.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${app.admin-email}")
    private String adminEmail;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAdminEmail(MailRequest request) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(adminEmail);

            // Subject: Full Name + Sub-Service
            helper.setSubject(request.getSubService() + " - " + request.getFullName());

            String emailContent =
                    "<h2 style='color:#2c3e50;'>New Consultation Request</h2>" +

                            "<table style='border-collapse: collapse; width: 100%; font-family: Arial, sans-serif;'>"

                            + "<tr>"
                            + "<td style='border:1px solid #ddd; padding:8px; font-weight:bold;'>Full Name</td>"
                            + "<td style='border:1px solid #ddd; padding:8px;'>" + request.getFullName() + "</td>"
                            + "</tr>"

                            + "<tr>"
                            + "<td style='border:1px solid #ddd; padding:8px; font-weight:bold;'>Company Name</td>"
                            + "<td style='border:1px solid #ddd; padding:8px;'>" + request.getCompanyName() + "</td>"
                            + "</tr>"

                            + "<tr>"
                            + "<td style='border:1px solid #ddd; padding:8px; font-weight:bold;'>Email</td>"
                            + "<td style='border:1px solid #ddd; padding:8px;'>" + request.getEmail() + "</td>"
                            + "</tr>"

                            + "<tr>"
                            + "<td style='border:1px solid #ddd; padding:8px; font-weight:bold;'>Phone</td>"
                            + "<td style='border:1px solid #ddd; padding:8px;'>"
                            + (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()
                            ? request.getPhoneNumber()
                            : "Not Provided")
                            + "</td>"
                            + "</tr>"

                            + "<tr>"
                            + "<td style='border:1px solid #ddd; padding:8px; font-weight:bold;'>Service</td>"
                            + "<td style='border:1px solid #ddd; padding:8px;'>" + request.getService() + "</td>"
                            + "</tr>"

                            + "<tr>"
                            + "<td style='border:1px solid #ddd; padding:8px; font-weight:bold;'>Sub-Service</td>"
                            + "<td style='border:1px solid #ddd; padding:8px;'>" + request.getSubService() + "</td>"
                            + "</tr>"

                            + "<tr>"
                            + "<td style='border:1px solid #ddd; padding:8px; font-weight:bold;'>Message</td>"
                            + "<td style='border:1px solid #ddd; padding:8px;'>" + request.getMessage() + "</td>"
                            + "</tr>"

                            + "</table>";

            helper.setText(emailContent, true); // true = HTML

            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAutoReply(MailRequest request) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(request.getEmail());
        helper.setSubject("Thank you for contacting Aaravi Consultancy");
        helper.setFrom("yourcompanyemail@gmail.com"); // same as SMTP username

        // HTML email content with inline logo and proper structure
        String htmlMsg = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<title>Aaravi Consultancy</title>" +
                "</head>" +
                "<body style='font-family: Arial, sans-serif; color:#333; line-height:1.5;'>" +

                // Container for email
                "<div style='max-width:600px; margin:0 auto; padding:20px; border:1px solid #eee; border-radius:8px;'>" +

                "<p>Hi " + request.getFullName() + ",</p>" +
                "<p>Thank you for reaching out to <b>Aaravi Consultancy</b>.</p>" +
                "<p>We have received your message and our team will contact you shortly.</p>" +
                "<p style='margin-top:20px;'>Warm regards,</p>" +
                "<img src='cid:aaraviLogo' width='120'/>" +
                "</div>" +
                "</body>" +
                "</html>";

        helper.setText(htmlMsg, true); // true = HTML

        // Inline logo from resources folder
        ClassPathResource logo = new ClassPathResource("static/images/logo.png");
        helper.addInline("aaraviLogo", logo);

        mailSender.send(mimeMessage);
    }
}
