package ru.mtuci.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.policy.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
