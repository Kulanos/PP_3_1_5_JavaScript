package org.kulanos.pp_3_1_3_bootsecurity.controller;

import org.kulanos.pp_3_1_3_bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String adminPage() {
        // model.addAttribute("users", userService.findAll());
        return "admin-page";
    }

    @GetMapping("/user")
    public String userPage() {
       // model.addAttribute("users", userService.findAll());
        return "user-page";
    }
}
