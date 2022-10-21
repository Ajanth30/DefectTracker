package com.example.EmployeeRegistration.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void SendVerificationMail(String to, String body) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Account Verification Mail");
        message.setText(body);
        javaMailSender.send(message);

    }
}
