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
class AttachMoServiceTest {

    @Autowired
    private AttachMoService attachMoService;

    @MockBean
    private AttachMoRepository attachMoRepository;

    @MockBean
    private OrgtypeRepository orgtypeRepository;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private DoctypeRepository doctypeRepository;

    @Test
    void testGet() {
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

        doReturn(Optional.of(attachMo)).when(attachMoRepository).findById(1L);

        AttachMo returnedAttachMo = attachMoService.get(1L);

        Assertions.assertSame(returnedAttachMo, attachMo);
    }

    @Test
    void testGetAll() {
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

        List<AttachMo> attachMoList = new ArrayList<>();
        attachMoList.add(attachMo);

        doReturn(attachMoList).when(attachMoRepository).findAll();

        List<AttachMo> returnedList = attachMoService.getAll();

        Assertions.assertSame(returnedList, attachMoList);
    }

    @Test
    void testSave() {
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

        attachMoService.save(attachMo);

        verify(attachMoRepository).save(argThat(entity -> entity.getId().equals(1L)));
    }

    @Test
    void testDelete() {
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
        attachMoService.delete(1L);

        Assertions.assertThrows(NoSuchElementException.class, () -> attachMoRepository.findById(1L).get());
    }

    @Test
    void testUpdate() {
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

        when(attachMoRepository.findById(1L)).thenReturn(Optional.of(attachMo));
        when(attachMoRepository.save(attachMo)).thenReturn(attachMo);

        final AttachMo result = attachMoService.update(1L, attachMo);
        Assertions.assertEquals(attachMo, result);
    }
}
