package com.progettodogsitting.dogsitting_backend.DTO;

import lombok.Data;

@Data
public class UtenteDTO {

    
    private String username;
    private String nomeBattesimo;
    private String cognome;
    private String cap;
    private String nCivico;
    private String provincia;
    private String via;
    private String nTel;
    private String ruolo; // "cliente" o "dogsitter"

}
