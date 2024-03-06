package com.endropioz.schoolrestapp.mail.service;

public interface MailService {
    void sendEmail(String to, String subject, String body);
}
