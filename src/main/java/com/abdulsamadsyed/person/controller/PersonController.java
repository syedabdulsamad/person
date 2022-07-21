package com.abdulsamadsyed.person.controller;

import com.abdulsamadsyed.person.model.Person;
import com.abdulsamadsyed.person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/persons")
    public Mono<List<Person>> getPersons() {
        return personService.findAll();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> getPersons(@PathVariable Long id) throws InterruptedException {
        System.out.println("Call received with id:" + id);
        return personService.findOne(id);
    }
//

    @PostMapping("/persons")
    public Mono<Person> createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }
}
