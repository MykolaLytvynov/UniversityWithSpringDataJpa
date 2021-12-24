package ua.com.foxminded.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.entities.Faculty;



@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Faculty f SET f.name = ?1, f.description = ?2 WHERE f.id = ?3")
    void update (String name, String description, Integer id);
}
