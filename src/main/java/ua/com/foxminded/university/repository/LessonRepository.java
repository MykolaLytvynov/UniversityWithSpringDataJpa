package ua.com.foxminded.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.entities.ClassRoom;
import ua.com.foxminded.university.entities.Group;
import ua.com.foxminded.university.entities.Lesson;
import ua.com.foxminded.university.entities.Subject;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Lesson l SET l.classRoom = ?1, l.dateTime = ?2, l.duration = ?3, l.subject = ?4, " +
            "l.groups = ?5 WHERE l.id = ?6")
    void update(ClassRoom classRoom, LocalDateTime dateTime, Integer duration, Subject subject, List<Group> groups, Integer id);

    List<Lesson> findAllByGroupsAndDateTimeBetween(Group group, LocalDateTime startTime, LocalDateTime endTime);

    List<Lesson> findAllBySubject_Teacher_IdAndDateTimeBetween(Integer id, LocalDateTime startTime, LocalDateTime endTime);
}
