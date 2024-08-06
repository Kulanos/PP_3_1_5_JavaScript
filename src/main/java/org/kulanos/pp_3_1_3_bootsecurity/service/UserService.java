package org.kulanos.pp_3_1_3_bootsecurity.service;

import org.kulanos.pp_3_1_3_bootsecurity.model.Role;
import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.kulanos.pp_3_1_3_bootsecurity.repository.RoleRepository;
import org.kulanos.pp_3_1_3_bootsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
       return userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        Set<Role> roles = user.getRoles().stream()
                .map(role -> roleRepository.findById(role.getId()).orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        userRepository.save(user);
    }



    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User findByUserEmail(String email) {
        return  userRepository.getUserByEmail(email);
    }
}
