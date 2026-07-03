package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "UTENTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utente {

    // Identifica la chiave primaria della tabella
    @Id
    @Column(name = "USERNAME", nullable = false)
    private String username;

    // Colonna per il nome di battesimo
    @Column(name = "NOME_BATT", nullable = false)
    private String nomeBattesimo;

    // Colonna per il cognome
    @Column(name = "COGNOME", nullable = false)
    private String cognome;

    // Colonna per il codice di avviamento postale
    @Column(name = "CAP", nullable = false)
    private String cap;

    // Colonna per il numero civico
    @Column(name = "N_CIVICO", nullable = false)
    private String nCivico;

    // Colonna per la provincia
    @Column(name = "PROVINCIA", nullable = false)
    private String provincia;

    // Colonna per la via di residenza
    @Column(name = "VIA", nullable = false)
    private String via;

    // Colonna per il numero di telefono
    @Column(name = "N_TEL", nullable = false)
    private String nTel;

    // Colonna per memorizzare la password utente
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    // Indica che questo attributo non deve essere persistito sul DB (usato solo a runtime)
    @Transient 
    private String ruolo;
}
