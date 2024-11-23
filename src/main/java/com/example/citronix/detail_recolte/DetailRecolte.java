package com.example.citronix.detail_recolte;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.recolte.Recolte;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@Entity
public class DetailRecolte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "arbre_id", nullable = false)
    private Arbre arbre;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;

    private Double quantite;
}
