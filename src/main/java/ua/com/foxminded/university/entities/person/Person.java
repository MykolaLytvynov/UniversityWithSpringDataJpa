package ua.com.foxminded.university.entities.person;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "Must be only letters")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 25, message = "Last name should be between 2 and 25 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "Must be only letters")
    private String lastName;
}
