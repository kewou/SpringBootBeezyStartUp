package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "logements")
@Getter
@Setter
public class Logement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private Long id;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @JsonIgnore
    public User getUser() {
        return this.user;
    }
}
