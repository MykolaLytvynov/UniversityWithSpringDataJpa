package ua.com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.entities.ClassRoom;


@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE ClassRoom c SET c.name = ?1, c.description = ?2 WHERE c.id = ?3")
    void update(String name, String description, Integer id);

}
