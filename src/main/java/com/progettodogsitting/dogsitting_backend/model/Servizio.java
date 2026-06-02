package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entità che rappresenta un tipo di Servizio offerto nell'app.
 * Corrisponde alla tabella SERVIZIO nel database.
 *
 * Ogni servizio ha una categoria (es. "dogsitting", "toelettatura") e una durata.
 * La relazione con il dogsitter è gestita dalla tabella OFFRE tramite ServizioOfferto.
 */
@Data
@Entity
@Table(name = "servizio")
public class Servizio {

    /** ID auto-generato del servizio. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Categoria del servizio (es. "dogsitting", "addestramento"). */
    @Column(name = "categoria", nullable = false)
    private String categoria;

    /** Durata del servizio espressa in minuti. */
    @Column(name = "durata", nullable = false)
    private Integer durata;
}
