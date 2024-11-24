package com.example.citronix.recolte;

import com.example.citronix.champ.Champ;
import com.example.citronix.detail_recolte.DetailRecolte;
import com.example.citronix.enumeration.Saison;
import com.example.citronix.vente.Vente;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "La date de récolte ne peut pas être null")
    private LocalDate dateRecolte;

    @NotNull(message = "La saison ne peut pas être null")
    private Saison saison;

    @PositiveOrZero(message = "La quantité doit être positive ou égale à zéro")
    private Double quantite;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<DetailRecolte> detailsRecolte;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    @NotNull(message = "Le champ associé ne peut pas être null")
    private Champ champ;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vente> ventes;
}
