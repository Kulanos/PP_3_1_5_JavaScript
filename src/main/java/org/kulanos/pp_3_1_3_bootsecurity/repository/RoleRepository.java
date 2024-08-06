package org.kulanos.pp_3_1_3_bootsecurity.repository;

import org.kulanos.pp_3_1_3_bootsecurity.model.Role;
import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findRoleById(Long id);
}
