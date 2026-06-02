package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entità che rappresenta un Campo di Addestramento.
 * Corrisponde alla tabella CAMPO_ADDESTRAMENTO nel database.
 *
 * I campi vengono gestiti dall'amministratore e possono ospitare Lezioni.
 */
@Data
@Entity
@Table(name = "campo_addestramento")
public class CampoAddestramento {

    /** Nome del campo — chiave primaria (identificativo testuale univoco). */
    @Id
    @Column(name = "nome", nullable = false)
    private String nome;

    /** Via in cui si trova il campo. */
    @Column(name = "via", nullable = false)
    private String via;

    /** Numero civico dell'indirizzo del campo. */
    @Column(name = "n_civico", nullable = false)
    private String nCivico;

    /** CAP dell'indirizzo del campo. */
    @Column(name = "cap", nullable = false)
    private String cap;

    /** Provincia dell'indirizzo del campo. */
    @Column(name = "provincia", nullable = false)
    private String provincia;

    /** Numero di telefono del campo. */
    @Column(name = "n_tel", nullable = false)
    private String nTel;

    /** Orario di apertura del campo (es. "09:00-18:00"). */
    @Column(name = "orario_apertura", nullable = false)
    private String orarioApertura;
}
