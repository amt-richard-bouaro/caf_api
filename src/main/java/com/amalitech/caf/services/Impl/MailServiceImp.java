package com.amalitech.caf.services.Impl;

import com.amalitech.caf.services.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImp implements MailService {
    
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    public MailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    @Override
    public void sendEmail(
            String to,
            String subject,
            String body
    ) {
        
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        
    }
    
    
    @Override
    public void sendHtmlEmail(
            String to,
            String subject,
            String htmlBody
    ) throws MessagingException, UnsupportedEncodingException {
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.setFrom(new InternetAddress(fromEmail, "Amalitech Localhost"));
        mailSender.send(message);
    }
}
