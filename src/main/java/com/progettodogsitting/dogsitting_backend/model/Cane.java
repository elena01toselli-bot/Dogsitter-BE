package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entità che rappresenta un Cane registrato nell'app Dogsitter.
 * Corrisponde alla tabella CANE nel database.
 *
 * Ogni cane appartiene a un Cliente (relazione many-to-one tramite usernameCliente).
 */
@Data
@Entity
@Table(name = "cane")
public class Cane {

    /** Numero di microchip del cane — chiave primaria univoca. */
    @Id
    @Column(name = "n_microchip", nullable = false)
    private String nMicrochip;

    /** Nome del cane. */
    @Column(name = "nome", nullable = false)
    private String nome;

    /** Razza del cane. */
    @Column(name = "razza", nullable = false)
    private String razza;

    /** Taglia del cane (es. "piccola", "media", "grande"). */
    @Column(name = "taglia", nullable = false)
    private String taglia;

    /** Data di nascita del cane in formato stringa (es. "2020-05-10"). */
    @Column(name = "data_nascita")
    private String dataNascita;

    /** Note sul comportamento del cane (campo libero). */
    @Column(name = "note_comportamento")
    private String noteComportamento;

    /**
     * Username del cliente proprietario del cane.
     * Chiave esterna verso la tabella CLIENTE.
     */
    @Column(name = "username_cliente", nullable = false)
    private String usernameCliente;
}
