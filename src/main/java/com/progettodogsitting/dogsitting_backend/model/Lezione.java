package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "LEZIONE")
public class Lezione {


    @EmbeddedId
    private LezioneId id;

    @Column(name = "MAX_PARTECIPANTI", nullable = false)
    private Integer maxPartecipanti;

    // nome_campo è sia parte della PK che FK verso CampoAddestramento
    @ManyToOne
    @MapsId("nomeCampo") 
    @JoinColumn(name = "NOME_CAMPO")
    private CampoAddestramento campoAddestramento;

    // tipologia è solo FK, non fa parte della PK
    @ManyToOne
    @JoinColumn(name = "TIPOLOGIA")
    private TipologiaLezione tipologiaLezione;
}