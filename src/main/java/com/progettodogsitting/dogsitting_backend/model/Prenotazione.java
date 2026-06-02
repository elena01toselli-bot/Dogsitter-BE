package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entità che rappresenta una Prenotazione nell'app Dogsitter.
 * Corrisponde alla tabella PRENOTAZIONE nel database.
 *
 * Una prenotazione può riguardare un servizio dogsitter oppure una lezione
 * in un campo di addestramento (distinto dal campo categoriaServizio).
 */
@Data
@Entity
@Table(name = "prenotazione")
public class Prenotazione {

    /** ID auto-generato della prenotazione. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codice_id")
    private Integer codiceId;

    /** Nome del cane prenotato. */
    @Column(name = "nome_cane", nullable = false)
    private String nomeCane;

    /** Numero di microchip del cane prenotato (FK → CANE). */
    @Column(name = "n_microchip", nullable = false)
    private String nMicrochip;

    /**
     * Categoria del servizio prenotato: "lezione" (campo) o "dogsitter".
     * Determina se la prenotazione è per una lezione in campo o per un dogsitter.
     */
    @Column(name = "categoria_servizio", nullable = false)
    private String categoriaServizio;

    /** Data di svolgimento della prenotazione (es. "2025-06-15"). */
    @Column(name = "data_svolgimento", nullable = false)
    private String dataSvolgimento;

    /** Ora di svolgimento della prenotazione (es. "10:00"). */
    @Column(name = "ora_svolgimento", nullable = false)
    private String oraSvolgimento;

    /** Prezzo concordato per la prenotazione. */
    @Column(name = "prezzo_pattuito", nullable = false)
    private Double prezzoPattuito;

    /** Username del cliente che ha effettuato la prenotazione (FK → CLIENTE). */
    @Column(name = "username_cliente")
    private String usernameCliente;

    /** Username del dogsitter coinvolto nella prenotazione (FK → DOG_SITTER, opzionale). */
    @Column(name = "username_dogsitter")
    private String usernameDogsitter;
}
