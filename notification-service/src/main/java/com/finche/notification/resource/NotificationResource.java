package com.finche.notification.resource;

import com.finche.db.model.Company;
import com.finche.notification.model.Recipient;
import com.finche.notification.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/notification/")
@Slf4j
public class NotificationResource {

    @Autowired
    private EmailService emailService;

    @Value("${finche.registration.notification:Hi}")
    private String registrationMessage;

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getMessage() {
        Recipient recipient = new Recipient();
        recipient.setAccountName("Damola");
        recipient.setEmail("damolacoker@gmail.com");
        String text = "Hey Damola, you are almost ready to start enjoying finche. Simply click the big yellow button below to verify your email address.";
        try {
            emailService.sendWithTemplate(recipient, "Welcome", text);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hello world";
    }

    @PostMapping(value = "/sendnotification", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void sendNotification(@Valid @RequestBody Optional<Company> company) {
        if (company.isPresent()) {
            company.ifPresent(comp -> {
                Recipient recipient = new Recipient(comp.getName(), comp.getCompanyEmail());
                try {
                    emailService.sendWithTemplate(recipient, "Finche Registration", registrationMessage);
                } catch (Exception e) {
                    log.error("Error in Sending Notification: {}", e);

                }
            });
        }
        log.error("Company not Created");
    }
}
