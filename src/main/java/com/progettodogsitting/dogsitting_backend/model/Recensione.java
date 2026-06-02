package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entità che rappresenta una Recensione lasciata da un Cliente a un Dogsitter.
 * Corrisponde alla tabella RECENSIONE nel database.
 *
 * Chiave primaria composta: (usernameCliente, usernameDogsitter).
 */
@Data
@Entity
@Table(name = "recensione")
public class Recensione {

    /**
     * Chiave primaria composta: (usernameCliente, usernameDogsitter).
     * Garantisce che ogni cliente lasci al massimo una recensione per dogsitter.
     */
    @EmbeddedId
    private RecensioneId id;

    /**
     * Username del cliente — esposto direttamente per comodità.
     * insert/updatable = false perché fa parte dell'id.
     */
    @Column(name = "username_cliente", insertable = false, updatable = false)
    private String usernameCliente;

    /**
     * Username del dogsitter — esposto direttamente per comodità.
     * insert/updatable = false perché fa parte dell'id.
     */
    @Column(name = "username_dogsitter", insertable = false, updatable = false)
    private String usernameDogsitter;

    /** Voto numerico della recensione (es. da 1 a 5). */
    @Column(name = "voto", nullable = false)
    private Integer voto;

    /** Commento testuale lasciato dal cliente. */
    @Column(name = "commento")
    private String commento;

    /** Data in cui è stata scritta la recensione (es. "2025-06-01"). */
    @Column(name = "data", nullable = false)
    private String data;
}
