package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Embeddable
@NoArgsConstructor 
@AllArgsConstructor
public class LezioneId implements Serializable {


    @Column(name = "NOME_CAMPO")
    private String nomeCampo;

    @Column(name = "ORA")
    private LocalTime ora;

    @Column(name = "DATA")
    private LocalDate data;
}