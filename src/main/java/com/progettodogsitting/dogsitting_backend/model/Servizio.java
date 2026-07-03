package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;

// Entità che mappa la tabella "servizio"
@Data
@Entity
@Table(name = "SERVIZIO")
public class Servizio {   

    // Identificativo univoco autogenerato
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    // Categoria del servizio (es: passeggiata, toelettatura)
    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;

    // Durata del servizio in minuti
    @Column(name = "DURATA", nullable = false)
    private Integer durata;
}
