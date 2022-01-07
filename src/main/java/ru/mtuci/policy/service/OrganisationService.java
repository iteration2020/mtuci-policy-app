package ru.mtuci.policy.service;

import org.springframework.stereotype.Service;
import ru.mtuci.policy.dao.OrganisationRepository;
import ru.mtuci.policy.model.Organisation;

import java.util.List;

@Service
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    public OrganisationService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public Organisation get(Long id) {
        return organisationRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Organisation> getAll() {
        return organisationRepository.findAll();
    }

    public void save(Organisation organisation) {
        organisationRepository.save(organisation);
    }

    public void delete(Long id) {
        organisationRepository.deleteById(id);
    }

    public Organisation update(Long id, Organisation organisation) {
        organisation.setId(id);
        return organisationRepository.save(organisation);
    }
}
