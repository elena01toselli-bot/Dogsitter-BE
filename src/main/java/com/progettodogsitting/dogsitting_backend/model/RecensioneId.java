package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

// Chiave composta per la tabella delle recensioni
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RecensioneId implements Serializable {

    // Riferimento al cliente che scrive la recensione
    @Column(name = "USERNAME_C")
    private String usernameCliente;

    // Riferimento al dogsitter che riceve la recensione
    @Column(name = "USERNAME_D")
    private String usernameDogsitter;
}
