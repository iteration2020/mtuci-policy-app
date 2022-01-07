package ru.mtuci.policy.controller;

import org.springframework.web.bind.annotation.*;
import ru.mtuci.policy.model.Person;
import ru.mtuci.policy.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("{id}")
    public Person get(@PathVariable("id") Long id) {
        return personService.get(id);
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @PostMapping
    public void save(@RequestBody Person person) {
        personService.save(person);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Person person) {
        personService.update(id, person);
    }
}
