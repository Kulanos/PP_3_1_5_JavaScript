package org.kulanos.pp_3_1_3_bootsecurity.controller;


import org.hibernate.event.spi.PreInsertEvent;
import org.kulanos.pp_3_1_3_bootsecurity.model.Role;
import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.kulanos.pp_3_1_3_bootsecurity.service.RoleService;
import org.kulanos.pp_3_1_3_bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public String homeAdminPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin-page";
    }

    @GetMapping("/admin/add-user")
    public String showAddUserPage(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = roleService.findAll();
        model.addAttribute("allRoles", roles);
        return "admin-add-user";
    }

    @PostMapping("/admin/add-user")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.findAll();
        model.addAttribute("allRoles", roles);
        return "admin-add-user";
    }


    @GetMapping("/user")
    public String homeUserPage(Authentication aut, Model model) {
        String email = aut.getName();
        model.addAttribute("user",userService.findByUserEmail(email));

        return "user-page";
    }




}
