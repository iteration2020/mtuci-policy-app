package ru.mtuci.policy.service;

import org.springframework.stereotype.Service;
import ru.mtuci.policy.dao.PersonRepository;
import ru.mtuci.policy.model.Person;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person get(Long id) {
        return personRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Person update(Long id, Person person) {
        person.setId(id);
        return personRepository.save(person);
    }

}
