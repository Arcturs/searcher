package ru.urfu.olympiad.searcher.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.olympiad.searcher.model.Person;
import ru.urfu.olympiad.searcher.service.PersonService;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/")
    public ResponseEntity<?> getAllPeopleByFio(@RequestParam String fio) {
        return ResponseEntity.ok(personService.getPeopleByFio(fio));
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(personService.createPerson(person));
    }

}
