package ru.urfu.olympiad.searcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.urfu.olympiad.searcher.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM person
            WHERE first_name LIKE concat('%', :argument, '%')
                OR second_name LIKE concat('%', :argument, '%')
                OR middle_name LIKE concat('%', :argument, '%')
            """)
    List<Person> findAllByOneArgument(String argument);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM person
            WHERE (first_name LIKE concat('%', :argument1, '%')
                AND second_name LIKE concat('%', :argument2, '%'))
                OR (second_name LIKE concat('%', :argument1, '%')
                AND first_name LIKE concat('%', :argument2, '%'))
            """)
    List<Person> findAllByNameAndSurname(String argument1, String argument2);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM person
            WHERE (first_name LIKE concat('%', :argument1, '%')
                AND middle_name LIKE concat('%', :argument2, '%'))
                OR (middle_name LIKE concat('%', :argument1, '%')
                AND first_name LIKE concat('%', :argument2, '%'))
                OR (second_name LIKE concat('%', :argument1, '%')
                AND middle_name LIKE concat('%', :argument2, '%'))
                OR (middle_name LIKE concat('%', :argument1, '%')
                AND second_name LIKE concat('%', :argument2, '%'))
            """)
    List<Person> findAllByNameAndSurnameIncludingMiddleName(String argument1, String argument2);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM person
            WHERE (first_name LIKE concat('%', :argument1, '%')
                AND second_name LIKE concat('%', :argument2, '%')
                AND middle_name LIKE concat('%', :argument3, '%'))
                OR (second_name LIKE concat('%', :argument1, '%')
                AND first_name LIKE concat('%', :argument2, '%')
                AND middle_name LIKE concat('%', :argument3, '%'))
            """)
    List<Person> findAllByFrequentFioCombination(String argument1, String argument2, String argument3);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM person
            WHERE (middle_name LIKE concat('%', :argument1, '%')
                AND first_name LIKE concat('%', :argument2, '%')
                AND second_name LIKE concat('%', :argument3, '%'))
                OR (middle_name LIKE concat('%', :argument1, '%')
                AND second_name LIKE concat('%', :argument2, '%')
                AND first_name LIKE concat('%', :argument3, '%'))
            """)
    List<Person> findAllByFioCombinationWhereMiddleNameIsFirst(String argument1, String argument2, String argument3);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM person
            WHERE (first_name LIKE concat('%', :argument1, '%')
                AND middle_name LIKE concat('%', :argument2, '%')
                AND second_name LIKE concat('%', :argument3, '%'))
                OR (second_name LIKE concat('%', :argument1, '%')
                AND middle_name LIKE concat('%', :argument2, '%')
                AND first_name LIKE concat('%', :argument3, '%'))
            """)
    List<Person> findAllByFioCombinationWhereMiddleNameIsSecond(String argument1, String argument2, String argument3);

}
