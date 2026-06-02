package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entità che rappresenta la tabella ponte OFFRE tra Dogsitter e Servizio.
 * Corrisponde alla relazione "un dogsitter offre uno o più servizi a un certo prezzo".
 *
 * Viene usata come DTO dal frontend: contiene già
 * i campi categoria e durata del Servizio associato.
 */
@Data
@Entity
@Table(name = "offre")
public class ServizioOfferto {

    /** ID auto-generato del record "offre". */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idServizio;

    /** Username del dogsitter che offre il servizio (FK → DOG_SITTER). */
    @Column(name = "username_dogsitter", nullable = false)
    private String usernameDogsitter;

    /**
     * Categoria del servizio (es. "dogsitting").
     * Denormalizzata qui per comodità del frontend.
     */
    @Column(name = "categoria", nullable = false)
    private String categoria;

    /** Durata del servizio in minuti. */
    @Column(name = "durata", nullable = false)
    private Integer durata;

    /** Prezzo di listino del servizio per questo dogsitter. */
    @Column(name = "prezzo_listino")
    private Double prezzoListino;
}
