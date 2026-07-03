package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "DOG_SITTER")
@PrimaryKeyJoinColumn(name = "USERNAME_D")
public class Dogsitter extends Utente {

    // Specifica il limite massimo di cani che il dogsitter può gestire
    @Column(name = "MAX_CANI", nullable = false)
    private Integer maxCani;

    @ElementCollection
    @CollectionTable(name = "GIORNI_DOG_SITTER", joinColumns = @JoinColumn(name = "USERNAME_D"))
    @Column(name = "GIORNO_DISPONIBILE")
    private List<String> giorniDisponibili;

    // Collezione di elementi per definire le taglie dei cani gestibili
    @ElementCollection
    @CollectionTable(name = "TAGLIE_CANI", joinColumns = @JoinColumn(name = "USERNAME_D"))
    @Column(name = "TAGLIA")
    private List<String> taglieCani;
}
