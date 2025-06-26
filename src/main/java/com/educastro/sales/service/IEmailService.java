package com.educastro.sales.service;

public interface IEmailService {
    void sendEmail(String to, String subject, String text);
    void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment);
    void sendHtmlMessage(String to, String subject, String htmlBody);
}
