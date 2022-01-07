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
class OrganisationServiceTest {

    @Autowired
    private OrganisationService organisationService;

    @MockBean
    private OrganisationRepository organisationRepository;

    @MockBean
    private OrgtypeRepository orgtypeRepository;


    @Test
    void testGet() {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);

        doReturn(Optional.of(organisation)).when(organisationRepository).findById(1L);

        Organisation returnedOrganisation = organisationService.get(1L);

        Assertions.assertSame(returnedOrganisation, organisation);
    }

    @Test
    void testGetAll() {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);

        List<Organisation> organisations = new ArrayList<>();
        organisations.add(organisation);

        doReturn(organisations).when(organisationRepository).findAll();

        List<Organisation> returnedList = organisationService.getAll();

        Assertions.assertSame(returnedList, organisations);
    }

    @Test
    void testSave() {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setId(1L);
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);
        organisationService.save(organisation);

        verify(organisationRepository).save(argThat(entity -> entity.getId().equals(1L)));
    }

    @Test
    void testDelete() {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);
        organisationRepository.save(organisation);

        organisationService.delete(1L);

        Assertions.assertThrows(NoSuchElementException.class, () -> organisationRepository.findById(1L).get());
    }

    @Test
    void testUpdate() {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("type");
        orgtypeRepository.save(orgtype);

        Organisation organisation = new Organisation();
        organisation.setNameorg("name");
        organisation.setHeadorg("head");
        organisation.setAddress("add");
        organisation.setOrgtype(orgtype);
        organisation.setId(1L);

        when(organisationRepository.findById(1L)).thenReturn(Optional.of(organisation));
        when(organisationRepository.save(organisation)).thenReturn(organisation);

        final Organisation result = organisationService.update(1L, organisation);
        Assertions.assertEquals(organisation, result);
    }
}
