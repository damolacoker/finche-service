package com.finche.db.client;

import com.finche.db.model.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "notification-service")
public interface NotificationServiceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/notification/sendnotification", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void sendNotification(Optional<Company> company);
}
