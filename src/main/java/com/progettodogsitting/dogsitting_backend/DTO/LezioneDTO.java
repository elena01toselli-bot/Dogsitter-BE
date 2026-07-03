package com.progettodogsitting.dogsitting_backend.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LezioneDTO {
    
    @EqualsAndHashCode.Include
    private String nomeCampo;

    @EqualsAndHashCode.Include
    private LocalDate data; 

    @EqualsAndHashCode.Include
    private LocalTime ora;

    // altri attributi della lezione che il frontend si aspetta
    private String tipologia; // ad esempio "agility", "tartufo", ecc.
    private BigDecimal costo; // costo della lezione
    private Integer maxPartecipanti; // numero massimo di partecipanti alla lezione
    
}
