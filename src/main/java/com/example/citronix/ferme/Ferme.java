package com.example.citronix.ferme;

import com.example.citronix.champ.Champ;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;


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

    @OneToMany(mappedBy = "ferme")
    private List<Champ> champs;

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

    public @NotNull(message = "La localisation ne peut pas être null") String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(@NotNull(message = "La localisation ne peut pas être null") String localisation) {
        this.localisation = localisation;
    }

    public @NotNull(message = "La superficie ne peut pas être null") Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(@NotNull(message = "La superficie ne peut pas être null") Double superficie) {
        this.superficie = superficie;
    }

    public @NotNull(message = "La date ne peut pas être null") Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(@NotNull(message = "La date ne peut pas être null") Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
