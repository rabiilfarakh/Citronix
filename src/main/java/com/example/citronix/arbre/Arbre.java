package com.example.citronix.arbre;

import com.example.citronix.champ.Champ;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.util.Date;
import java.util.UUID;

@Entity
public class Arbre {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "La date de plantation ne peut pas être null")
    private Date datePlantation;

    @ManyToOne
    private Champ champ;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull(message = "La date de plantation ne peut pas être null") Date getDatePlantation() {
        return datePlantation;
    }

    public void setDatePlantation(@NotNull(message = "La date de plantation ne peut pas être null") Date datePlantation) {
        this.datePlantation = datePlantation;
    }

    public Champ getChamp() {
        return champ;
    }

    public void setChamp(Champ champ) {
        this.champ = champ;
    }
}
