package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "CAMPO_ADDESTRAMENTO")
public class CampoAddestramento {

    // Nome del campo come identificatore univoco
    @Id
    @Column(name = "NOME", nullable = false)
    private String nome;

    // Indirizzo: via
    @Column(name = "VIA", nullable = false)
    private String via;

    // Indirizzo: numero civico
    @Column(name = "N_CIVICO", nullable = false)
    private String nCivico;

    // Indirizzo: CAP
    @Column(name = "CAP", nullable = false)
    private String cap;

    // Indirizzo: provincia 
    @Column(name = "PROVINCIA", nullable = false)
    private String provincia;

    // Contatto telefonico
    @Column(name = "N_TEL", nullable = false)
    private String nTel;

    // Orario di apertura quotidiano
    @Column(name = "ORARIO_A", nullable = false)
    private String orarioApertura; // Es.Lun-Sab 08:00 – 19:00
}
