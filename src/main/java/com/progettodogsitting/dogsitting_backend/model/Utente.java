package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entità base che rappresenta un utente dell'applicazione Dogsitter.
 * Corrisponde alla tabella UTENTE nel database.
 *
 * Viene estesa da Cliente e Dogsitter tramite ereditarietà JPA (JOINED).
 * Il ruolo differenzia le tre categorie: cliente, dogsitter, amministratore.
 */
@Data
@Entity
@Table(name = "utente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utente {

    /** Username dell'utente — chiave primaria (stringa scelta dall'utente). */
    // con nullable = false garantiamo che ogni utente abbia uno username
    
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    /** Nome di battesimo dell'utente. */
    @Column(name = "nome_battesimo", nullable = false)
    private String nomeBattesimo;

    /** Cognome dell'utente. */
    @Column(name = "cognome", nullable = false)
    private String cognome;

    /** CAP dell'indirizzo di residenza. */
    @Column(name = "cap", nullable = false)
    private String cap;

    /** Numero civico dell'indirizzo di residenza. */
    @Column(name = "n_civico", nullable = false)
    private String nCivico;

    /** Provincia dell'indirizzo di residenza. */
    @Column(name = "provincia", nullable = false)
    private String provincia;

    /** Via dell'indirizzo di residenza. */
    @Column(name = "via", nullable = false)
    private String via;

    /** Numero di telefono dell'utente. */
    @Column(name = "n_tel", nullable = false)
    private String nTel;

    /** Password dell'utente (in produzione va hashata, es. BCrypt). */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Ruolo dell'utente: "cliente", "dogsitter" o "amministratore".
     * Serve al frontend per decidere il routing post-login.
     */
    @Transient // non viene mappato su nessuna colonna del database
    private String ruolo;
}
