package org.example.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String nom;

    @Column(name = "telephone", nullable = false)
    private int telephone;

    @OneToMany(mappedBy = "user")
    private Set<Logement> logements;


}
