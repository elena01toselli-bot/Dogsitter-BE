package com.progettodogsitting.dogsitting_backend.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CANE")
public class Cane {

    // Identificativo univoco (microchip) come chiave primaria
    @Id
    @Column(name = "N_MICROCHIP", nullable = false)
    private String nMicrochip;

    // Nome del cane
    @Column(name = "NOME", nullable = false)
    private String nome;

    // Razza del cane
    @Column(name = "RAZZA", nullable = false)
    private String razza;

    // Taglia del cane
    @Column(name = "TAGLIA", nullable = false)
    private String taglia;

    // Data di nascita
    @Column(name = "DATA_NASCITA", nullable = false)
    private LocalDate dataNascita;

    // Note opzionali sul comportamento
    @Column(name = "NOTE_COMPORTAMENTO")
    private String noteComportamento;
   
    // Relazione ManyToOne = many(cani) to one(Cliente) con Cliente (proprietario del cane)
    // molte istanze di questa entità appartengono a un solo Cliente
    @ManyToOne
    @JoinColumn(name = "USERNAME_C", nullable = false)
    private Cliente cliente;
}
