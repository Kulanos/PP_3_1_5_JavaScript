package org.kulanos.pp_3_1_3_bootsecurity.repository;

import org.kulanos.pp_3_1_3_bootsecurity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
