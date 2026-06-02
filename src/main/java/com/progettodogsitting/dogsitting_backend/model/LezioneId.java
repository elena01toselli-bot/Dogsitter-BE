package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Classe che rappresenta la chiave primaria composta della Lezione.
 * JPA richiede che le chiavi composite implementino Serializable
 * e siano annotate con @Embeddable.
 *
 * La chiave è composta da: nomeCampo, ora, data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LezioneId implements Serializable {

    /** Nome del campo di addestramento (FK → CAMPO_ADDESTRAMENTO). */
    @Column(name = "nome_campo")
    private String nomeCampo;

    /** Ora della lezione (es. "10:00"). */
    @Column(name = "ora")
    private String ora;

    /** Data della lezione (es. "2025-06-15"). */
    @Column(name = "data")
    private String data;
}
