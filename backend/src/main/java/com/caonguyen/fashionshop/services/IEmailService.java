package com.caonguyen.fashionshop.services;

public interface IEmailService {
    void sendEmail(String to, String subject, String content);
    void sendHtmlEmail(String to, String subject, String content);
}
