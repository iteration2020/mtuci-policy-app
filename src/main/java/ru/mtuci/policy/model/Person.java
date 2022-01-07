package ru.mtuci.policy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "person")
public class Person extends AbstractBaseEntity {

    @Column(name = "LastName")
    private String lastname;

    @Column(name = "FirstName")
    private String firstname;

    @Column(name = "Patronymic")
    private String patronymic;

    @Column(name = "DateBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate datebirth;

    @Column(name = "PlaceBirth")
    private String placebirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DocId")
    private Doctype doc;

    @Column(name = "DocNumber")
    private String docnumber;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;
}
