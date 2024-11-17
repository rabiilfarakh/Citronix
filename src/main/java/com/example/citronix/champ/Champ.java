package com.example.citronix.champ;

import com.example.citronix.ferme.Ferme;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Champ {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Le nom ne peut pas être null")
    private String nom;

    @NotNull(message = "la superficie ne peut pas etre null")
    private Double superficie;

    @ManyToOne
    @NotNull(message = "La ferme ne peut pas être null")
    private Ferme ferme;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull(message = "Le nom ne peut pas être null") String getNom() {
        return nom;
    }

    public void setNom(@NotNull(message = "Le nom ne peut pas être null") String nom) {
        this.nom = nom;
    }

    public @NotNull(message = "la superficie ne peut pas etre null") Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(@NotNull(message = "la superficie ne peut pas etre null") Double superficie) {
        this.superficie = superficie;
    }
}
