package ru.mtuci.policy.service;

import org.springframework.stereotype.Service;
import ru.mtuci.policy.dao.AttachMoRepository;
import ru.mtuci.policy.model.AttachMo;

import java.util.List;

@Service
public class AttachMoService {

    private final AttachMoRepository attachMoRepository;

    public AttachMoService(AttachMoRepository attachMoRepository) {
        this.attachMoRepository = attachMoRepository;
    }

    public AttachMo get(Long id) {
        return attachMoRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<AttachMo> getAll() {
        return attachMoRepository.findAll();
    }

    public void save(AttachMo attachMo) {
        attachMoRepository.save(attachMo);
    }

    public void delete(Long id) {
        attachMoRepository.deleteById(id);
    }

    public AttachMo update(Long id, AttachMo attachMo) {
        attachMo.setId(id);
        return attachMoRepository.save(attachMo);
    }
}
