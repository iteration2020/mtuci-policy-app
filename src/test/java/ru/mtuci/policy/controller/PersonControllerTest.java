package ru.mtuci.policy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.mtuci.policy.dao.DoctypeRepository;
import ru.mtuci.policy.dao.PersonRepository;
import ru.mtuci.policy.model.Doctype;
import ru.mtuci.policy.model.Person;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctypeRepository doctypeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldStorePerson() throws Exception {
        Doctype doctype = new Doctype();
        doctype.setType("test type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setFirstname("first");
        person.setLastname("last");
        person.setPatronymic("pat");
        person.setDatebirth(LocalDate.now());
        person.setDoc(doctype);
        person.setEmail("test@mail.ru");
        person.setPhone("999222");
        person.setPlacebirth("Moscow");


        this.mockMvc
                .perform(
                        post("/api/v1/person/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(person))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetPerson() throws Exception {
        Doctype doctype = new Doctype();
        doctype.setType("test type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setFirstname("first");
        person.setLastname("last");
        person.setPatronymic("pat");
        person.setDatebirth(LocalDate.now());
        person.setDoc(doctype);
        person.setEmail("test@mail.ru");
        person.setPhone("999222");
        person.setPlacebirth("Moscow");
        personRepository.save(person);


        this.mockMvc
                .perform(
                        get("/api/v1/person/" + person.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname", is("last")))
                .andExpect(jsonPath("$.firstname", is("first")))
                .andExpect(jsonPath("$.patronymic", is("pat")))
                .andExpect(jsonPath("$.phone", is("999222")))
                .andExpect(jsonPath("$.doc").exists());
    }

    @Test
    public void shouldGetAllPerson() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/v1/person/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeletePerson() throws Exception {
        Doctype doctype = new Doctype();
        doctype.setType("test type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setFirstname("first");
        person.setLastname("last");
        person.setPatronymic("pat");
        person.setDatebirth(LocalDate.now());
        person.setDoc(doctype);
        person.setEmail("test@mail.ru");
        person.setPhone("999222");
        person.setPlacebirth("Moscow");
        personRepository.save(person);

        this.mockMvc
                .perform(
                        delete("/api/v1/person/" + person.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());

        Assertions.assertThrows(NoSuchElementException.class, () -> personRepository.findById(person.getId()).get());
    }

    @Test
    public void shouldUpdatePerson() throws Exception {
        Doctype doctype = new Doctype();
        doctype.setType("test type");
        doctypeRepository.save(doctype);

        Person person = new Person();
        person.setFirstname("first");
        person.setLastname("last");
        person.setPatronymic("pat");
        person.setDatebirth(LocalDate.now());
        person.setDoc(doctype);
        person.setEmail("test@mail.ru");
        person.setPhone("999222");
        person.setPlacebirth("Moscow");
        personRepository.save(person);

        person.setLastname("updated");

        this.mockMvc
                .perform(
                        put("/api/v1/person/" + person.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(person))
                )
                .andExpect(status().isOk());

        Assertions.assertEquals("updated", personRepository.findById(person.getId()).get().getLastname());
    }
}
