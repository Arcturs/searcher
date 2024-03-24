package ru.urfu.olympiad.searcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.urfu.olympiad.searcher.model.Person;
import ru.urfu.olympiad.searcher.repository.PersonRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Test
    void getAllPeopleByOnePartOfFio() {
        prepareTestData();

        var result = personService.getPeopleByFio("Гали");
        assertEquals(2, result.size());
        assertEquals("Демидович", result.get(0).getSecondName());
        assertEquals("Демидовна", result.get(1).getSecondName());

        result = personService.getPeopleByFio("Алекс");
        assertEquals(2, result.size());
        assertEquals("Демидовна", result.get(0).getSecondName());
        assertEquals("Верп", result.get(1).getSecondName());
    }

    @Test
    void getAllPeopleByTwoPartOfFio() {
        prepareTestData();

        var result = personService.getPeopleByFio("Демидов Галина");
        assertEquals(2, result.size());
        assertEquals("Демидович", result.get(0).getSecondName());
        assertEquals("Демидовна", result.get(1).getSecondName());

        result = personService.getPeopleByFio("Анатольевич Верп");
        assertEquals(1, result.size());
        assertEquals("Верп", result.get(0).getSecondName());
    }

    @Test
    void getAllPeopleByFio() {
        prepareTestData();

        var result = personService.getPeopleByFio("Верп Александр Анатольевич");
        assertEquals(1, result.size());
        assertEquals("Верп", result.get(0).getSecondName());
    }

    private void prepareTestData() {
        personRepository.saveAll(
                List.of(
                        new Person()
                                .setId(1L)
                                .setFirstName("Галина")
                                .setSecondName("Демидович"),
                        new Person()
                                .setId(2L)
                                .setFirstName("Галина")
                                .setSecondName("Демидовна")
                                .setMiddleName("Александровна"),
                        new Person()
                                .setId(3L)
                                .setFirstName("Александр")
                                .setSecondName("Верп")
                                .setMiddleName("Анатольевич"),
                        new Person()
                                .setId(4L)
                                .setFirstName("Дмитрий")
                                .setSecondName("Жук")
                                .setMiddleName("Дмитриевич")));
    }

}