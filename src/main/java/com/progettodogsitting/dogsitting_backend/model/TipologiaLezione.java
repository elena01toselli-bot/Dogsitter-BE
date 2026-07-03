package com.progettodogsitting.dogsitting_backend.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

// Elenco delle possibili discipline per l'addestramento
@Data
@Entity
@Table(name = "TIPOLOGIE_LEZIONI")
public class TipologiaLezione {

    // Nome della tipologia di lezione usata come identificatore
    @Id
    @Column(name = "TIPOLOGIA", nullable = false)
    private String tipologia;

    // Prezzo prestabilito per questa disciplina
    @Column(name = "COSTO", nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;
}
