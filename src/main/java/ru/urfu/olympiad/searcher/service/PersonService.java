package ru.urfu.olympiad.searcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.urfu.olympiad.searcher.model.Person;
import ru.urfu.olympiad.searcher.repository.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> getPeopleByFio(String fio) {
        if (fio == null || fio.isBlank()) {
            return List.of();
        }

        var fioParts = fio.split(" ");
        if (fioParts.length == 1) {
            return personRepository.findAllByOneArgument(fio);
        }

        if (fioParts.length == 2) {
            var people = personRepository.findAllByNameAndSurname(fioParts[0], fioParts[1]);
            return people.isEmpty()
                    ? personRepository.findAllByNameAndSurnameIncludingMiddleName(fioParts[0], fioParts[1])
                    : people;
        }

        var people = personRepository.findAllByFrequentFioCombination(fioParts[0], fioParts[1], fioParts[2]);
        if (!people.isEmpty()) {
            return people;
        }
        people = personRepository.findAllByFioCombinationWhereMiddleNameIsFirst(fioParts[0], fioParts[1], fioParts[2]);
        return people.isEmpty()
                ? personRepository.findAllByFioCombinationWhereMiddleNameIsSecond(fioParts[0], fioParts[1], fioParts[2])
                : people;
    }

    @Transactional
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

}
