package com.progettodogsitting.dogsitting_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entità che rappresenta un Cliente dell'app Dogsitter.
 * Estende Utente: aggiunge solo la relazione one-to-many con i Cani.
 * Corrisponde alla tabella CLIENTE (join con UTENTE).
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "username")
public class Cliente extends Utente {
    // Al momento non ci sono campi aggiuntivi rispetto a Utente.
    // I cani del cliente si recuperano tramite CaneRepository.
}
