package org.kulanos.pp_3_1_3_bootsecurity.controller;

import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.kulanos.pp_3_1_3_bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public User homeUserPage(Authentication auth) {
        String email = auth.getName();
        return userService.findByUserEmail(email);
    }
}
