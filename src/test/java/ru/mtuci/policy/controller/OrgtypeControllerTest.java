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
import ru.mtuci.policy.dao.OrgtypeRepository;
import ru.mtuci.policy.model.Orgtype;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrgtypeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrgtypeRepository orgtypeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldStoreOrgtype() throws Exception {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("test type");

        this.mockMvc
                .perform(
                        post("/api/v1/orgtype/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(orgtype))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOrgtype() throws Exception {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("test type");
        orgtypeRepository.save(orgtype);

        this.mockMvc
                .perform(
                        get("/api/v1/orgtype/" + orgtype.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("test type")));
    }

    @Test
    public void shouldGetAllOrgtype() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/v1/orgtype/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteOrgtype() throws Exception {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("test type");
        orgtypeRepository.save(orgtype);

        this.mockMvc
                .perform(
                        delete("/api/v1/orgtype/" + orgtype.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());

        Assertions.assertThrows(NoSuchElementException.class, () -> orgtypeRepository.findById(orgtype.getId()).get());
    }

    @Test
    public void shouldUpdateOrgtype() throws Exception {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("test type");
        orgtypeRepository.save(orgtype);

        Orgtype newOrgtype = new Orgtype();
        newOrgtype.setId(orgtype.getId());
        newOrgtype.setType("updated");

        this.mockMvc
                .perform(
                        put("/api/v1/orgtype/" + orgtype.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(newOrgtype))
                )
                .andExpect(status().isOk());

        Assertions.assertEquals("updated", orgtypeRepository.findById(orgtype.getId()).get().getType());
    }
}
