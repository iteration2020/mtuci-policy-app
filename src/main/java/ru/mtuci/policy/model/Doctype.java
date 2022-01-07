package ru.mtuci.policy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "doctype")
public class Doctype extends AbstractBaseEntity {

    @Column(name = "Type")
    private String type;
}
