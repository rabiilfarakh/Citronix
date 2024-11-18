package com.example.citronix.champ;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.ferme.Ferme;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
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
    private Ferme ferme;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "arbre_id")
    private List<Arbre> arbres;


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

    public Ferme getFerme() {
        return ferme;
    }

    public void setFerme(Ferme ferme) {
        this.ferme = ferme;
    }

    public List<Arbre> getArbres() {
        return arbres;
    }

    public void setArbres(List<Arbre> arbres) {
        this.arbres = arbres;
    }
}
