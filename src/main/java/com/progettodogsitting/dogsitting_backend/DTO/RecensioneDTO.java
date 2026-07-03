package com.progettodogsitting.dogsitting_backend.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RecensioneDTO {
    
    private String usernameCliente;       // FK al CLIENTE
    private String usernameDogsitter;     // FK al DOG_SITTER
    private Integer voto;
    private String commento;
    private LocalDate data;


}
