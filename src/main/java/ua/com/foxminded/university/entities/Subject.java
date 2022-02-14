package ua.com.foxminded.university.entities;

import lombok.*;
import ua.com.foxminded.university.entities.person.Employee;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @NonNull
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 2, max = 20, message = "Description should be between 2 and 35 characters")
    private String description;

    @NonNull
    @Column(name = "amount_lessons")
    @Min(value = 1, message = "Amount lessons should be greater than zero")
    private Integer amountLessons;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee teacher;

}
