package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entità che rappresenta una Lezione di addestramento.
 * Corrisponde alla tabella LEZIONE nel database.
 *
 * La chiave primaria è composta: (nomeCampo, ora, data).
 * Ogni lezione è associata a un CampoAddestramento.
 */
@Data
@Entity
@Table(name = "lezione")
public class Lezione {

    /**
     * Chiave primaria composta: (nomeCampo, ora, data).
     * Gestita dalla classe LezioneId.
     */
    @EmbeddedId
    private LezioneId id;

    /**
     * Nome del campo in cui si svolge la lezione.
     * Mapped da LezioneId, qui esposto come colonna per query più semplici.
     * insert/updatable = false perché fa parte dell'id.
     */
    @Column(name = "nome_campo", insertable = false, updatable = false)
    private String nomeCampo;

    /**
     * Ora della lezione (es. "10:00").
     * Esposta direttamente per comodità nel frontend.
     * insert/updatable = false perché fa parte dell'id.
     */
    @Column(name = "ora", insertable = false, updatable = false)
    private String ora;

    /**
     * Data della lezione (es. "2025-06-15").
     * Esposta direttamente per comodità nel frontend.
     * insert/updatable = false perché fa parte dell'id.
     */
    @Column(name = "data", insertable = false, updatable = false)
    private String data;

    /** Tipologia della lezione (es. "base", "avanzata", "agility"). */
    @Column(name = "tipologia", nullable = false)
    private String tipologia;

    /** Costo in euro della lezione. */
    @Column(name = "costo", nullable = false)
    private Double costo;

    /** Numero massimo di partecipanti ammessi alla lezione. */
    @Column(name = "max_partecipanti", nullable = false)
    private Integer maxPartecipanti;
}
