package com.example.citronix.arbre;

import com.example.citronix.champ.Champ;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter @Setter
@Entity
public class Arbre {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "La date de plantation ne peut pas être null")
    private Date datePlantation;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

}
