package com.caonguyen.fashionshop.services;

public interface IEmailService {
    String sendEmail(String to, String subject, String content);
    String sendHtmlEmail(String to, String subject, String content);
}
