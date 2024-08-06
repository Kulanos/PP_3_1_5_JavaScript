package org.kulanos.pp_3_1_3_bootsecurity.service;

import org.kulanos.pp_3_1_3_bootsecurity.model.Role;
import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.kulanos.pp_3_1_3_bootsecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }

    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }


}
