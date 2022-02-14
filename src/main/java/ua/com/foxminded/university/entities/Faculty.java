package ua.com.foxminded.university.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @NonNull
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 2, max = 20, message = "Description should be between 2 and 20 characters")
    private String description;

    @OneToMany(mappedBy = "faculty")
    @ToString.Exclude
    private List<Course> courses;

}
