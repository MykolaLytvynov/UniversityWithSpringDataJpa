package ua.com.foxminded.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.entities.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
