package com.amalitech.caf.services;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface MailService {
    
    void sendEmail(
            String to,
            String subject,
            String body
    );
    
    
    void sendHtmlEmail(
            String to,
            String subject,
            String htmlBody
    ) throws MessagingException, UnsupportedEncodingException;
}
