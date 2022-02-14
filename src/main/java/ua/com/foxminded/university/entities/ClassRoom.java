package ua.com.foxminded.university.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "classroom")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @NonNull
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 2, max = 20, message = "Description should be between 5 and 50 characters")
    private String description;
}