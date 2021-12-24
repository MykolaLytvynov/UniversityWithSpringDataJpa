package ua.com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.entities.Position;


@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Position p SET p.name = ?1 WHERE p.id = ?2")
    void update(String namePosition, Integer id);
}
