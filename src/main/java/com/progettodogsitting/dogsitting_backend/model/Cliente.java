package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
// Genera equals e hashCode chiamando anche i metodi della superclasse (Utente)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CLIENTE")
@PrimaryKeyJoinColumn(name = "USERNAME_C")
public class Cliente extends Utente {
    // Questa classe eredita tutti i campi da Utente e non definisce attributi aggiuntivi
}
