package ua.com.foxminded.university.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "date_time")
    @FutureOrPresent(message = "The value must be either the future or the present")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateTime;

    @NonNull
    @Min(value = 10, message = "Duration should be greater than 10")
    private Integer duration;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private ClassRoom classRoom;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Subject subject;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @NotEmpty(message = "The field should not be empty")
    @JoinTable(
            name = "groups_lessons",
            joinColumns = {@JoinColumn(name = "id_lesson")},
            inverseJoinColumns = {@JoinColumn(name = "id_group")}
    )
    List<Group> groups;

}
