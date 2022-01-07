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
import ru.mtuci.policy.dao.*;
import ru.mtuci.policy.model.AttachMo;
import ru.mtuci.policy.model.Doctype;
import ru.mtuci.policy.model.Orgtype;
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
public class AttachMoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    private AttachMoRepository attachMoRepository;

    @Autowired
    private OrgtypeRepository orgtypeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DoctypeRepository doctypeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldStoreAttachment() throws Exception {
        AttachMo attachMo = new AttachMo();
        attachMo.setId(1L);
        attachMo.setDateend(LocalDate.now());
        attachMo.setDatestart(LocalDate.now());

        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

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
        personRepository.save(person);

        attachMo.setOrg(orgtype);
        attachMo.setPerson(person);

        this.mockMvc
                .perform(
                        post("/api/v1/attachments/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(attachMo))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAttachment() throws Exception {
        AttachMo attachMo = new AttachMo();
        attachMo.setId(1L);
        attachMo.setDateend(LocalDate.now());
        attachMo.setDatestart(LocalDate.now());

        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

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
        personRepository.save(person);

        attachMo.setOrg(orgtype);
        attachMo.setPerson(person);
        attachMoRepository.save(attachMo);

        this.mockMvc
                .perform(
                        get("/api/v1/attachments/" + attachMo.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(attachMo.getId().intValue())))
                .andExpect(jsonPath("$.person").exists())
                .andExpect(jsonPath("$.org").exists());
    }

    @Test
    public void shouldGetAllAttachment() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/v1/attachments/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteAttachment() throws Exception {
        AttachMo attachMo = new AttachMo();
        attachMo.setId(1L);
        attachMo.setDateend(LocalDate.now());
        attachMo.setDatestart(LocalDate.now());

        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

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
        personRepository.save(person);

        attachMo.setOrg(orgtype);
        attachMo.setPerson(person);
        attachMoRepository.save(attachMo);

        this.mockMvc
                .perform(
                        delete("/api/v1/attachments/" + attachMo.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());

        Assertions.assertThrows(NoSuchElementException.class, () -> attachMoRepository.findById(attachMo.getId()).get());
    }

    @Test
    public void shouldUpdateAttachment() throws Exception {
        AttachMo attachMo = new AttachMo();
        attachMo.setId(1L);
        attachMo.setDateend(LocalDate.now());
        attachMo.setDatestart(LocalDate.now());

        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

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
        personRepository.save(person);

        attachMo.setOrg(orgtype);
        attachMo.setPerson(person);
        attachMoRepository.save(attachMo);

        LocalDate yd = LocalDate.now().minusDays(1);
        attachMo.setDatestart(yd);

        this.mockMvc
                .perform(
                        put("/api/v1/attachments/" + attachMo.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(attachMo))
                )
                .andExpect(status().isOk());
    }
}
