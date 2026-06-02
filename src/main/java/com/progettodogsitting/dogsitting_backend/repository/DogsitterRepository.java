package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Dogsitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'entità Dogsitter.
 * Fornisce le operazioni CRUD su DOG_SITTER (join con UTENTE).
 * La chiave primaria è lo username (String), ereditato da Utente.
 */
@Repository
public interface DogsitterRepository extends JpaRepository<Dogsitter, String> {
    // Le query CRUD base sono già fornite da JpaRepository.
}
