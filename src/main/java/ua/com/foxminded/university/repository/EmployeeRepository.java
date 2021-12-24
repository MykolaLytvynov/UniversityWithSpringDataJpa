package ua.com.foxminded.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.entities.Position;
import ua.com.foxminded.university.entities.person.Employee;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.name = ?1, e.lastName = ?2, e.salary = ?3, e.position =?4 WHERE e.id = ?5")
    void update(String name, String lastName, Integer salary, Position position, Integer id);


    @Query("SELECT DISTINCT e FROM Employee e INNER JOIN Subject s on s.teacher.id = e.id")
    List<Employee> getAllTeacher();
}
