package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.ServizioOfferto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per l'entità ServizioOfferto (tabella OFFRE).
 * Fornisce le operazioni CRUD sulla tabella ponte tra Dogsitter e Servizio.
 */
@Repository
public interface ServizioOffertoRepository extends JpaRepository<ServizioOfferto, Integer> {

    /**
     * Recupera tutti i servizi offerti da un determinato dogsitter.
     *
     * @param usernameDogsitter lo username del dogsitter
     * @return lista dei servizi offerti
     */
    List<ServizioOfferto> findByUsernameDogsitter(String usernameDogsitter);
}
