package com.example.citronix.arbre;

import com.example.citronix.champ.Champ;
import com.example.citronix.detail_recolte.DetailRecolte;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@Entity
public class Arbre {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "La date de plantation ne peut pas être null")
    private LocalDate datePlantation;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    @OneToMany(mappedBy = "arbre")
    private Set<DetailRecolte> recoltes;

}
