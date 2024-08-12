package org.kulanos.pp_3_1_3_bootsecurity.controller;


import org.kulanos.pp_3_1_3_bootsecurity.model.Role;
import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.kulanos.pp_3_1_3_bootsecurity.service.RoleService;
import org.kulanos.pp_3_1_3_bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/admin/add-user")
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
    public User homeUserPage(Authentication auth) {
        String email = auth.getName();
        return userService.findByUserEmail(email);
    }

    // Новый метод для получения списка ролей
    @GetMapping("/api/roles")
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

}
