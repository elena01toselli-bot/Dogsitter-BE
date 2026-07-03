package com.progettodogsitting.dogsitting_backend.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "OFFRE")
public class Offre {

    // Chiave primaria composta (username_d, id_servizio)
    @EmbeddedId
    private OffreId id;

    // Collegamento in sola lettura alla tabella dogsitter
    @ManyToOne
    @MapsId("usernameDogsitter") 
    @JoinColumn(name = "USERNAME_D")
    private Dogsitter dogsitter;

    @ManyToOne
    @MapsId("idServizio")
    @JoinColumn(name = "ID_SERVIZIO")
    private Servizio servizio;

    // Prezzo fissato dal dogsitter per questo servizio
    @Column(name = "PREZZO_LISTINO",precision = 10, scale = 2)
    private BigDecimal prezzoListino;
}
