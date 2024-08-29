package org.kulanos.pp_3_1_3_bootsecurity.repository;

import org.kulanos.pp_3_1_3_bootsecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findRoleById(Long id);
}
