package com.progettodogsitting.dogsitting_backend.model;

import java.math.BigDecimal;


import jakarta.persistence.*;
import lombok.Data;

// Entità che rappresenta una singola prenotazione per cani o lezioni
@Data
@Entity
@Table(name = "PRENOTAZIONE")
public class Prenotazione {

    // ID testuale generato univocamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODICE_ID", nullable = false)
    private Integer codiceId;   

    // Il costo accordato per la prestazione
    @Column(name = "PREZZO_PATTUITO", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoPattuito;

    // Tipo di prestazione (es. dog sitting, lezione di addestramento)
    @Column(name = "TIPOLOGIA_ATTIVITA", nullable = false)
    private String tipologiaAttivita;


    // prenotazione.getLezione().getCampoAddestramento(); sempre disponibile
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "NOME_CAMPO", referencedColumnName = "NOME_CAMPO"),
        @JoinColumn(name = "ORA_LEZIONE", referencedColumnName = "ORA"),
        @JoinColumn(name = "DATA_LEZIONE", referencedColumnName = "DATA")
    })
    private Lezione lezione;

    // Cane associato alla prenotazione
    @ManyToOne
    @JoinColumn(name = "N_MICROCHIP", nullable = false)
    private Cane cane;
}
