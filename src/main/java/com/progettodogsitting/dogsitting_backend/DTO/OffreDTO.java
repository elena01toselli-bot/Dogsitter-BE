package com.progettodogsitting.dogsitting_backend.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OffreDTO {

    private Integer idServizio;
    private String usernameDogsitter;
    private String categoria;
    private Integer durata;
    private BigDecimal prezzoListino;  

    
}
