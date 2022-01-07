package ru.mtuci.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.policy.model.Orgtype;

public interface OrgtypeRepository extends JpaRepository<Orgtype, Long> {
}
