package ru.mtuci.policy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mtuci.policy.dao.OrgtypeRepository;
import ru.mtuci.policy.model.Orgtype;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrgtypeServiceTest {

    @Autowired
    private OrgtypeService orgtypeService;

    @MockBean
    private OrgtypeRepository orgtypeRepository;

    @Test
    void testGet() {
        Orgtype orgtype = new Orgtype();
        orgtype.setId(1L);
        orgtype.setType("type 1");

        doReturn(Optional.of(orgtype)).when(orgtypeRepository).findById(1L);

        Orgtype returnedOrgtype = orgtypeService.get(1L);

        Assertions.assertSame(returnedOrgtype, orgtype);
    }

    @Test
    void testGetAll() {
        Orgtype orgtype1 = new Orgtype();
        orgtype1.setId(1L);
        orgtype1.setType("type 1");

        Orgtype orgtype2 = new Orgtype();
        orgtype2.setId(2L);
        orgtype2.setType("type 2");

        List<Orgtype> orgtypeList = new ArrayList<>();
        orgtypeList.add(orgtype1);
        orgtypeList.add(orgtype2);

        doReturn(orgtypeList).when(orgtypeRepository).findAll();

        List<Orgtype> returnedList = orgtypeService.getAll();

        Assertions.assertSame(returnedList, orgtypeList);
    }

    @Test
    void testSave() {
        Orgtype orgtype = new Orgtype();
        orgtype.setType("type 1");

        orgtypeService.save(orgtype);

        verify(orgtypeRepository).save(argThat(entity -> entity.getType().equals("type 1")));
    }

    @Test
    void testDelete() {
        Orgtype orgtype = new Orgtype();
        orgtype.setId(1L);
        orgtype.setType("type 1");

        orgtypeRepository.save(orgtype);
        orgtypeService.delete(1L);

        Assertions.assertThrows(NoSuchElementException.class, () -> orgtypeRepository.findById(1L).get());
    }

    @Test
    void testUpdate() {
        Orgtype orgtype = new Orgtype();
        orgtype.setId(1L);
        orgtype.setType("type 1");
        orgtypeRepository.save(orgtype);

        when(orgtypeRepository.findById(1L)).thenReturn(Optional.of(orgtype));
        when(orgtypeRepository.save(orgtype)).thenReturn(orgtype);

        final Orgtype result = orgtypeService.update(1L, orgtype);

        Assertions.assertEquals(orgtype, result);
    }
}
