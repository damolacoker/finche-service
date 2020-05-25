package com.finche.notification.service;

import com.finche.notification.model.Recipient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Slf4j
@Service
public class EmailService implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Override
    public void send(Recipient recipient, String subject, String message) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(recipient.getEmail());
        helper.setSubject(subject);
        helper.setText(message);
        mailSender.send(mimeMessage);
        log.info("Email notification has been sent to {}", recipient.getEmail());
    }

    @Override
    public void sendWithTemplate(Recipient recipient, String subject, String message) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(recipient.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContentBuilder.build(message), true);
        mailSender.send(mimeMessage);
        log.info("Email notification has been sent to {}", recipient.getEmail());
    }

    @Override
    public void send(Recipient recipient) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(recipient.getEmail());
        helper.setSubject("subject");
        helper.setText("message");
        mailSender.send(mimeMessage);
        log.info("Email notification has been sent to {}", recipient.getEmail());
    }
}
