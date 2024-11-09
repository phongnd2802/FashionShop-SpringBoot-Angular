package com.caonguyen.fashionshop.services.impl;

import com.caonguyen.fashionshop.services.IEmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class EmailServiceImpl implements IEmailService {
    private static final String EMAIL_HOST = "FashionShopCN.com";

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(EMAIL_HOST);
        try {
            mailSender.send(message);
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(EMAIL_HOST);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
