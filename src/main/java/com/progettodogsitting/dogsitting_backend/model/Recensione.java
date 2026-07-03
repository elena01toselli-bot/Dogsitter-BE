package com.progettodogsitting.dogsitting_backend.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

// Entità che mappa la tabella "recensisce"
@Data
@Entity
@Table(name = "RECENSISCE")
public class Recensione {

    // Chiave primaria composta (username_c, username_d)
    @EmbeddedId
    private RecensioneId id;

    // Relazione verso Cliente
    @ManyToOne
    @MapsId("usernameCliente") // Mappa sulla proprietà di RecensioneId
    @JoinColumn(name = "USERNAME_C")
    private Cliente cliente;

    // Relazione verso Dogsitter
    @ManyToOne
    @MapsId("usernameDogsitter") // Mappa sulla proprietà di RecensioneId
    @JoinColumn(name = "USERNAME_D")
    private Dogsitter dogsitter;

    // Voto dato dal cliente
    @Column(name = "VOTO", nullable = false)
    private Integer voto;

    // Commento di testo libero
    @Column(name = "COMMENTO")
    private String commento;

    // Data in cui è stata lasciata la recensione
    @Column(name = "DATA", nullable = false)
    private LocalDate data;
}
