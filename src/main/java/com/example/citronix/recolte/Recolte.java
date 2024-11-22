package com.example.citronix.recolte;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.champ.Champ;
import com.example.citronix.detail_recolte.DetailRecolte;
import com.example.citronix.enumeration.Saison;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@Entity
public class Recolte {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "La date de recolte ne peut pas être null")
    private LocalDate dateRecolte;

    @NotNull(message = "La saison ne peut pas être null")
    private Saison saison;

    @Positive
    @NotNull(message = "La quantité ne peut pas être null")
    private Double quantite;

    @OneToMany(mappedBy = "recolte")
    private Set<DetailRecolte> arbres;

//    @ManyToOne
//    @JoinColumn(name = "champ_id", nullable = false)
//    private Champ champ;
}
