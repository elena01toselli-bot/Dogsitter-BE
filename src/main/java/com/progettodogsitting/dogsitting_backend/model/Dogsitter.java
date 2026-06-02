package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Entità che rappresenta un Dogsitter dell'app Dogsitter.
 * Estende Utente e aggiunge informazioni specifiche del ruolo:
 * numero massimo di cani gestibili, giorni disponibili e taglie accettate.
 * Corrisponde alla tabella DOG_SITTER (join con UTENTE).
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dog_sitter")
@PrimaryKeyJoinColumn(name = "username")
public class Dogsitter extends Utente {

    /** Numero massimo di cani che il dogsitter può gestire contemporaneamente. */
    @Column(name = "max_cani", nullable = false)
    private Integer maxCani;

    /**
     * Giorni della settimana in cui il dogsitter è disponibile.
     * Corrisponde alla tabella GIORNI_DOG_SITTER (join con username).
     * Memorizzati come lista di stringhe (es. "Lunedì", "Martedì").
     */
    @ElementCollection
    @CollectionTable(name = "giorni_dog_sitter",
            joinColumns = @JoinColumn(name = "username"))
    @Column(name = "giorno")
    private List<String> giorniDisponibili;

    /**
     * Taglie dei cani che il dogsitter accetta (es. "piccola", "media", "grande").
     * Corrisponde alla tabella TAGLIE_CANI (join con username).
     */
    @ElementCollection
    @CollectionTable(name = "taglie_cani",
            joinColumns = @JoinColumn(name = "username"))
    @Column(name = "taglia")
    private List<String> taglieCani;
}
