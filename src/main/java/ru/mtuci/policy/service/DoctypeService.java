package ru.mtuci.policy.service;

import org.springframework.stereotype.Service;
import ru.mtuci.policy.dao.DoctypeRepository;
import ru.mtuci.policy.model.Doctype;

import java.util.List;

@Service
public class DoctypeService {

    private final DoctypeRepository doctypeRepository;

    public DoctypeService(DoctypeRepository doctypeRepository) {
        this.doctypeRepository = doctypeRepository;
    }

    public Doctype get(Long id) {
        return doctypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Doctype> getAll() {
        return doctypeRepository.findAll();
    }

    public Doctype save(Doctype doctype) {
        return doctypeRepository.save(doctype);
    }

    public void delete(Long id) {
        doctypeRepository.deleteById(id);
    }

    public Doctype update(Long id, Doctype doctype) {
        doctype.setId(id);
        return doctypeRepository.save(doctype);
    }
}
