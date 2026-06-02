package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Chiave primaria composta per la Recensione.
 * La PK è formata da usernameCliente + usernameDogsitter:
 * un cliente può lasciare al massimo una recensione per dogsitter.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RecensioneId implements Serializable {

    /** Username del cliente che ha scritto la recensione. */
    @Column(name = "username_cliente")
    private String usernameCliente;

    /** Username del dogsitter recensito. */
    @Column(name = "username_dogsitter")
    private String usernameDogsitter;
}
