package com.progettodogsitting.dogsitting_backend.DTO;

import java.util.List;

import lombok.Data;

@Data
public class RegistrazioneDTO {
    // Campi comuni Utente
    private String username;
    private String password;
    private String nomeBattesimo;
    private String cognome;
    private String cap;
    private String nCivico;
    private String provincia;
    private String via;
    private String nTel;
    private String ruolo; // "cliente" o "dogsitter"

    // Campi specifici Dogsitter (null se è un cliente)
    private Integer maxCani;
    private List<String> giorniDisponibili;
    private List<String> taglieCani;
}
