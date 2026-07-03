package com.progettodogsitting.dogsitting_backend.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class PrenotazioneDTO {
    

    private Integer codiceId;
    private BigDecimal prezzoPattuito;
    private String categoriaPrenotazione; // 'lezione' (Campo) oppure 'dogsitter' (Dogsitter)
    private String nMicrochip;
    private LocalDate dataSvolgimento;
    private LocalTime oraSvolgimento;

    private String nomeCampo; // presente solo se categoriaPrenotazione è 'lezione'
    private String CategoriaLezione; // presente solo se categoriaPrenotazione è 'lezione'

    private String categoriaServizio; // presente solo se categoriaPrenotazione è 'dogsitter', altrimenti è null
    private Integer idServizio; // presente solo se categoriaPrenotazione è 'dogsitter', altrimenti è null
    private String usernameDogsitter;

    private String usernameCliente; 
    private String nomeCane; 


}
