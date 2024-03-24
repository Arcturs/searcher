package ru.urfu.olympiad.searcher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
public class Person {

    @Id
    @NotNull(message = "ИД пользователя должно присутствовать")
    private Long id;

    @NotBlank(message = "Имя пользователя должно присутствовать")
    private String firstName;

    @NotBlank(message = "Фамилия пользователя должно присутствовать")
    private String secondName;
    private String middleName;

}
