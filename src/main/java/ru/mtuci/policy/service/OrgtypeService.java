package ru.mtuci.policy.service;

import org.springframework.stereotype.Service;
import ru.mtuci.policy.dao.OrgtypeRepository;
import ru.mtuci.policy.model.Orgtype;

import java.util.List;

@Service
public class OrgtypeService {

    private final OrgtypeRepository orgtypeRepository;

    public OrgtypeService(OrgtypeRepository orgtypeRepository) {
        this.orgtypeRepository = orgtypeRepository;
    }

    public Orgtype get(Long id) {
        return orgtypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Orgtype> getAll() {
        return orgtypeRepository.findAll();
    }

    public void save(Orgtype orgtype) {
        orgtypeRepository.save(orgtype);
    }

    public void delete(Long id) {
        orgtypeRepository.deleteById(id);
    }

    public Orgtype update(Long id, Orgtype orgtype) {
        orgtype.setId(id);
        return orgtypeRepository.save(orgtype);
    }
}
