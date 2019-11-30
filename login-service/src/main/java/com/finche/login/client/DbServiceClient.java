package com.finche.login.client;

import com.finche.db.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "db-service")
public interface DbServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user/{username}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User getUser(@PathVariable("username") String username);

}
