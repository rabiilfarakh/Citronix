package com.example.citronix.champ;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.ferme.Ferme;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Entity
public class Champ {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Le nom ne peut pas être null")
    private String nom;

    @NotNull(message = "la superficie ne peut pas etre null")
    private Double superficie;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL)
    private List<Arbre> arbres = new ArrayList<>();

}
