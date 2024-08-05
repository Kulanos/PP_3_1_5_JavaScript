package org.kulanos.pp_3_1_3_bootsecurity.controller;


import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.kulanos.pp_3_1_3_bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MyController {

    @Autowired
    private UserService userService;


    @GetMapping("/admin")
    public String homeAdminPage(Model model) {
        List<User> users = userService.findAll();
        Map<User, String> usersWithRoles = users.stream()
                .collect(Collectors.toMap(
                        user -> user,
                        user -> user.getRoles().stream()
                                .map(role -> role.getRoleName().replace("ROLE_", ""))
                                .collect(Collectors.joining(" "))
                ));

        model.addAttribute("usersWithRoles", usersWithRoles);
        return "admin-page";
    }

    @GetMapping("/user")
    public String homeUserPage(@AuthenticationPrincipal UserDetails details, Model model) {
        String email = details.getUsername();
        User user = userService.findByUserEmail(email);

        model.addAttribute("user", user);
        return "user-page";
    }

//    @GetMapping("/user")
//    public String userPage() {
//       // model.addAttribute("users", userService.findAll());
//        return "user-page";
//    }
}
