package com.example.citronix.ferme;

import com.example.citronix.champ.Champ;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter @Getter
@Entity
public class Ferme {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Le nom ne peut pas être null")
    private String nom;

    @NotNull(message = "La localisation ne peut pas être null")
    private String localisation;

    @NotNull(message = "La superficie ne peut pas être null")
    private Double superficie;

    @NotNull(message = "La date ne peut pas être null")
    private Date dateCreation;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL)
    private List<Champ> champs;


}
