package org.kulanos.pp_3_1_3_bootsecurity.repository;

import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from  User u where  u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}
