package org.kulanos.pp_3_1_3_bootsecurity.repository;

import org.kulanos.pp_3_1_3_bootsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {

   @Query("SELECT u FROM User u WHERE u.email = :email")
   public User getUserByEmail(@Param("email") String email);


}
