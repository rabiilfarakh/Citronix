package com.example.citronix.vente;

import com.example.citronix.recolte.Recolte;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Le CIN ne doit pas être null")
    @Column(unique = true)
    private String cin;

    @NotNull(message = "La date ne doit pas être null")
    private LocalDate date;

    @PositiveOrZero(message = "La quantité doit être positive ou égale à zéro")
    private Double quantite;

    @PositiveOrZero(message = "Le prix doit être positif ou égal à zéro")
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "recolte_id")
    private Recolte recolte;
}
