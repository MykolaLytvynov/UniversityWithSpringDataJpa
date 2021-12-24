package ua.com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.entities.Group;
import ua.com.foxminded.university.entities.person.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.name = ?1, s.lastName = ?2, s.group = ?3 WHERE s.id = ?4")
    void update (String name, String lastName, Group group, Integer id);
}
