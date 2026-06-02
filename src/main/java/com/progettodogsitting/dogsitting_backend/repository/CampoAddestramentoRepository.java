package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.CampoAddestramento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'entità CampoAddestramento.
 * Fornisce le operazioni CRUD sulla tabella CAMPO_ADDESTRAMENTO.
 * La chiave primaria è il nome del campo (String).
 */
@Repository
public interface CampoAddestramentoRepository extends JpaRepository<CampoAddestramento, String> {
    // Le query CRUD base sono già fornite da JpaRepository.
}
