package ru.mtuci.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.policy.model.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
}
