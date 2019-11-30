package com.finche.registration.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

   @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getMessage(){
        return "Hello world";
    }
}
