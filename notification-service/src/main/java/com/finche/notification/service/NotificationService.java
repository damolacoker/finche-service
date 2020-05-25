package com.finche.notification.service;

import com.finche.notification.model.Recipient;

import javax.mail.MessagingException;
import java.io.IOException;

public interface NotificationService {
    void send(Recipient recipient, String subject, String message) throws MessagingException, IOException;

    void sendWithTemplate(Recipient recipient, String subject, String message) throws MessagingException, IOException;

    void send(Recipient recipient) throws MessagingException, IOException;
}
