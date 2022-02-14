package ua.com.foxminded.university.entities;

import lombok.*;
import ua.com.foxminded.university.entities.person.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @Size(min = 2, max = 25, message = "Name should be between 2 and 25 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @OneToMany(mappedBy = "position")
    @ToString.Exclude
    private List<Employee> employees;

}
