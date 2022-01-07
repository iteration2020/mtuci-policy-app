package ru.mtuci.policy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "organisations")
public class Organisation extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TypeOrg")
    private Orgtype orgtype;

    @Column(name = "Address")
    private String address;

    @Column(name = "NameOrg")
    private String nameorg;

    @Column(name = "HeadOrg")
    private String headorg;


}
