package org.kulanos.pp_3_1_3_bootsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.kulanos.pp_3_1_3_bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String homeAdminPage() {
        return "admin-page";
    }

    @GetMapping("/user")
    public String homeUserPage() {
        return "user-page";
    }

//    @GetMapping("/user")
//    public String userPage() {
//       // model.addAttribute("users", userService.findAll());
//        return "user-page";
//    }
}
