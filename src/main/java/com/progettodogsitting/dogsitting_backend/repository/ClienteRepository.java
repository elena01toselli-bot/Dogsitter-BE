package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'entità Cliente.
 * Fornisce le operazioni CRUD su CLIENTE (join con UTENTE).
 * La chiave primaria è lo username (String), ereditato da Utente.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    // Le query CRUD base (findAll, findById, save, deleteById)
    // sono già fornite da JpaRepository.
}
