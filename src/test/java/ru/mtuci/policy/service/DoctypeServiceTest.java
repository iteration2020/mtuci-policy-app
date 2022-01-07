package ru.mtuci.policy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mtuci.policy.dao.DoctypeRepository;
import ru.mtuci.policy.model.Doctype;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class DoctypeServiceTest {

    @Autowired
    private DoctypeService doctypeService;

    @MockBean
    private DoctypeRepository doctypeRepository;

    @Test
    void testGet() {
        Doctype doctype = new Doctype();
        doctype.setId(1L);
        doctype.setType("type 1");

        doReturn(Optional.of(doctype)).when(doctypeRepository).findById(1L);

        Doctype returnedDoctype = doctypeService.get(1L);

        Assertions.assertSame(returnedDoctype, doctype);
    }

    @Test
    void testGetAll() {
        Doctype doctype1 = new Doctype();
        doctype1.setId(1L);
        doctype1.setType("type 1");

        Doctype doctype2 = new Doctype();
        doctype2.setId(2L);
        doctype2.setType("type 2");

        List<Doctype> doctypeList = new ArrayList<>();
        doctypeList.add(doctype1);
        doctypeList.add(doctype2);

        doReturn(doctypeList).when(doctypeRepository).findAll();

        List<Doctype> returnedList = doctypeService.getAll();

        Assertions.assertSame(returnedList, doctypeList);
    }

    @Test
    void testSave() {
        Doctype doctype = new Doctype();
        doctype.setType("type 1");

        doctypeService.save(doctype);

        verify(doctypeRepository).save(argThat(entity -> entity.getType().equals("type 1")));
    }

    @Test
    void testDelete() {
        Doctype doctype = new Doctype();
        doctype.setId(1L);
        doctype.setType("type 1");

        doctypeRepository.save(doctype);
        doctypeService.delete(1L);

        Assertions.assertThrows(NoSuchElementException.class, () -> doctypeRepository.findById(1L).get());
    }

    @Test
    void testUpdate() {
        Doctype doctype = new Doctype();
        doctype.setId(1L);
        doctype.setType("type 1");
        doctypeRepository.save(doctype);

        when(doctypeRepository.findById(1L)).thenReturn(Optional.of(doctype));
        when(doctypeRepository.save(doctype)).thenReturn(doctype);

        final Doctype result = doctypeService.update(1L, doctype);
        Assertions.assertEquals(doctype, result);
    }
}
