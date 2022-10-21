package com.example.EmployeeRegistration.Services;

public interface EmailService {
    void SendVerificationMail(String to,String body);
}
