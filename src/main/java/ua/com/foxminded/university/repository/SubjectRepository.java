package ua.com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.entities.Subject;
import ua.com.foxminded.university.entities.person.Employee;

import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Subject s SET s.name = ?1, s.description = ?2, s.amountLessons = ?3, s.teacher = ?4 WHERE s.id = ?5")
    void update(String nameSubject, String descriptionSubject, Integer amountLessons, Employee employee, Integer id);

    List<Subject> findAllByTeacher_Id(Integer id);

    List<Subject> findByTeacherNull();
}
