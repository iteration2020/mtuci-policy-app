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
import ru.mtuci.policy.dao.OrganisationRepository;
import ru.mtuci.policy.dao.OrgtypeRepository;
import ru.mtuci.policy.model.Organisation;
import ru.mtuci.policy.model.Orgtype;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrganisationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrgtypeRepository orgtypeRepository;

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldStoreOrganisation() throws Exception {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("test type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);

        this.mockMvc
                .perform(
                        post("/api/v1/organisations/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(orgtype))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOrganisation() throws Exception {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("test type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);
        organisationRepository.save(organisation);

        this.mockMvc
                .perform(
                        get("/api/v1/organisations/" + organisation.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(organisation.getId().intValue())))
                .andExpect(jsonPath("$.orgtype").exists());
    }

    @Test
    public void shouldGetAllOrgtype() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/v1/organisations/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteOrganisation() throws Exception {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("test type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);
        organisationRepository.save(organisation);

        this.mockMvc
                .perform(
                        delete("/api/v1/organisations/" + organisation.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());

        Assertions.assertThrows(NoSuchElementException.class, () -> organisationRepository.findById(organisation.getId()).get());
    }

    @Test
    public void shouldUpdateOrganisation() throws Exception {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("test type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);
        organisationRepository.save(organisation);

        organisation.setNameorg("new updated");

        this.mockMvc
                .perform(
                        put("/api/v1/organisations/" + organisation.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(organisation))
                )
                .andExpect(status().isOk());

        Assertions.assertEquals("new updated", organisationRepository.findById(organisation.getId()).get().getNameorg());
    }
}
