package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

// Chiave composta per la tabella "offre"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OffreId implements Serializable {

    // Username del dogsitter, mappato su "username_d"
    @Column(name = "USERNAME_D")
    private String usernameDogsitter;

    // ID del servizio offerto, mappato su "id_servizio"
    @Column(name = "ID_SERVIZIO")
    private Integer idServizio;
}
