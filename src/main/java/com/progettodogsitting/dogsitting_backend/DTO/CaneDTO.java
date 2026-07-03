package com.progettodogsitting.dogsitting_backend.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CaneDTO {


    private String nMicrochip; // PK
    private String nome;
    private String razza;
    private String taglia;
    private LocalDate dataNascita;
    private String noteComportamento;
    private String usernameCliente; // FK al CLIENTE

    
}
