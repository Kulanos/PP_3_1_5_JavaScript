package org.kulanos.pp_3_1_3_bootsecurity.controller;


import org.kulanos.pp_3_1_3_bootsecurity.model.Role;
import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.kulanos.pp_3_1_3_bootsecurity.service.RoleService;
import org.kulanos.pp_3_1_3_bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public List<User> homeAdminPage() {
        return userService.findAll();
    }



    @PostMapping("admin/add-user")
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }


    @DeleteMapping("/admin/delete-user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        } else {
            userService.deleteUser(id);
        }
    }

    @PutMapping("/admin/update-user/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody User userDetails) {
        User user = userService.findUserById(id);

        if (user == null) {
            throw new NoSuchElementException("User not found");
        }

        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setRoles(userDetails.getRoles());
        userService.saveUser(user);

    }

        @GetMapping("/user")
        public User homeUserPage (Authentication auth){
            String email = auth.getName();
            return userService.findByUserEmail(email);
        }


//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleService roleService;
//
//    @GetMapping("/admin")
//    public String homeAdminPage(Model model) {
//        List<User> users = userService.findAll();
//        model.addAttribute("users", users);
//        return "admin-page";
//    }
//
//    @GetMapping("/admin/add-user")
//    public String showAddUserPage(Model model) {
//        model.addAttribute("user", new User());
//        List<Role> roles = roleService.findAll();
//        model.addAttribute("allRoles", roles);
//        return "admin-add-user";
//    }
//
//    @PostMapping("/admin/add-user")
//    public String addUser(@ModelAttribute User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/admin/delete-user/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/admin/update-user/{id}")
//    public String updateUser(@PathVariable("id") Long id, Model model) {
//        User user = userService.findUserById(id);
//        model.addAttribute("user", user);
//        List<Role> roles = roleService.findAll();
//        model.addAttribute("allRoles", roles);
//        return "admin-add-user";
//    }
//
//
//    @GetMapping("/user")
//    public String homeUserPage(Authentication aut, Model model) {
//        String email = aut.getName();
//        model.addAttribute("user",userService.findByUserEmail(email));
//
//        return "user-page";
//    }


    }
