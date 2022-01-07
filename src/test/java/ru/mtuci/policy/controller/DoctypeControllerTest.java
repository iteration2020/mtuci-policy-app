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
import ru.mtuci.policy.dto.DoctypeDto;
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
public class DoctypeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DoctypeRepository doctypeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldStoreDoctype() throws Exception {
        DoctypeDto doctypeDto = new DoctypeDto();
        doctypeDto.setType("test type");

        this.mockMvc
                .perform(
                        post("/api/v1/doctype/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(doctypeDto))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetDoctype() throws Exception {
        Doctype doctype = new Doctype();
        doctype.setType("test type");
        doctypeRepository.save(doctype);

        this.mockMvc
                .perform(
                        get("/api/v1/doctype/" + doctype.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("test type")));
    }

    @Test
    public void shouldGetAllDoctype() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/v1/doctype/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteDoctype() throws Exception {
        Doctype doctype = new Doctype();
        doctype.setType("test type");
        doctypeRepository.save(doctype);

        this.mockMvc
                .perform(
                        delete("/api/v1/doctype/" + doctype.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());

        Assertions.assertThrows(NoSuchElementException.class, () -> doctypeRepository.findById(doctype.getId()).get());
    }

    @Test
    public void shouldUpdateDoctype() throws Exception {
        Doctype doctype = new Doctype();
        doctype.setType("test type");
        doctypeRepository.save(doctype);

        Doctype newDoctype = new Doctype();
        newDoctype.setType("updated");

        this.mockMvc
                .perform(
                        put("/api/v1/doctype/" + doctype.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(DoctypeDto.fromDoctype(newDoctype)))
                )
                .andExpect(status().isOk());

        Assertions.assertEquals("updated", doctypeRepository.findById(doctype.getId()).get().getType());
    }
}
