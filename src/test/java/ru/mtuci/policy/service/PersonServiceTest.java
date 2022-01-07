package ru.mtuci.policy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mtuci.policy.dao.*;
import ru.mtuci.policy.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private DoctypeRepository doctypeRepository;

    @Test
    void testGet() {
        Doctype doctype = new Doctype();
        doctype.setType("type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setLastname("1");
        person.setFirstname("1");
        person.setPatronymic("1");
        person.setPlacebirth("1");
        person.setPhone("1");
        person.setEmail("1");
        person.setDoc(doctype);
        person.setDatebirth(LocalDate.now());

        doReturn(Optional.of(person)).when(personRepository).findById(1L);

        Person returnedPerson = personService.get(1L);

        Assertions.assertSame(returnedPerson, person);
    }

    @Test
    void testGetAll() {
        Doctype doctype = new Doctype();
        doctype.setType("type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setLastname("1");
        person.setFirstname("1");
        person.setPatronymic("1");
        person.setPlacebirth("1");
        person.setPhone("1");
        person.setEmail("1");
        person.setDoc(doctype);
        person.setDatebirth(LocalDate.now());

        List<Person> personList = new ArrayList<>();
        personList.add(person);

        doReturn(personList).when(personRepository).findAll();

        List<Person> returnedList = personService.getAll();

        Assertions.assertSame(returnedList, personList);
    }

    @Test
    void testSave() {
        Doctype doctype = new Doctype();
        doctype.setType("type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setLastname("1");
        person.setFirstname("1");
        person.setPatronymic("1");
        person.setPlacebirth("1");
        person.setPhone("1");
        person.setEmail("1");
        person.setDoc(doctype);
        person.setDatebirth(LocalDate.now());
        person.setId(1L);

        personService.save(person);

        verify(personRepository).save(argThat(entity -> entity.getId().equals(1L)));
    }

    @Test
    void testDelete() {
        Doctype doctype = new Doctype();
        doctype.setType("type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setLastname("1");
        person.setFirstname("1");
        person.setPatronymic("1");
        person.setPlacebirth("1");
        person.setPhone("1");
        person.setEmail("1");
        person.setDoc(doctype);
        person.setDatebirth(LocalDate.now());
        person.setId(1L);

        personRepository.save(person);
        personService.delete(1L);

        Assertions.assertThrows(NoSuchElementException.class, () -> personRepository.findById(1L).get());
    }

    @Test
    void testUpdate() {
        Doctype doctype = new Doctype();
        doctype.setType("type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setLastname("1");
        person.setFirstname("1");
        person.setPatronymic("1");
        person.setPlacebirth("1");
        person.setPhone("1");
        person.setEmail("1");
        person.setDoc(doctype);
        person.setDatebirth(LocalDate.now());

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);

        final Person result = personService.update(1L, person);
        Assertions.assertEquals(person, result);
    }
}
