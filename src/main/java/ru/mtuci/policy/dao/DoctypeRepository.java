package ru.mtuci.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.policy.model.Doctype;

public interface DoctypeRepository extends JpaRepository<Doctype, Long> {
}
