package com.finche.login.resource;

import com.finche.login.model.FincheUser;
import com.finche.login.service.FincheUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login/")
public class LoginController {

    @Autowired
    private FincheUserService fincheUserService;

    @GetMapping("/get/")
    public String getHello() {
        return "Hello world";
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping(value = "/createuser")
    public void createUser(@Valid @RequestBody FincheUser user) {
        fincheUserService.create(user);
    }

}
