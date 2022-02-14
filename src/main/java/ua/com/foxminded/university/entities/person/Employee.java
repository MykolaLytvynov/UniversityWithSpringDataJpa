package ua.com.foxminded.university.entities.person;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ua.com.foxminded.university.entities.Position;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "employees")
public class Employee extends Person {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Position position;
    @NonNull
    @Min(value = 0, message = "Salary should be greater than zero")
    private Integer salary;
}
