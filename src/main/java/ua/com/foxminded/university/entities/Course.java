package ua.com.foxminded.university.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @Column(name = "number_course")
    @Min(value = 1, message = "Number of course should be greater than one")
    private Integer numberCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Faculty faculty;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Group> groups;

}
