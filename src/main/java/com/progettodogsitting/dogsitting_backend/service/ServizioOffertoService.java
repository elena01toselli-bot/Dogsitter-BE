package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.ServizioOfferto;
import com.progettodogsitting.dogsitting_backend.repository.ServizioOffertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione dei Servizi Offerti dai Dogsitter.
 * Gestisce le operazioni CRUD sulla tabella ponte OFFRE.
 */
@Service
public class ServizioOffertoService {

    @Autowired
    private ServizioOffertoRepository repository;

    /**
     * Recupera tutti i servizi offerti da un determinato dogsitter.
     * @param usernameDogsitter lo username del dogsitter
     * @return lista dei servizi offerti dal dogsitter
     */
    public List<ServizioOfferto> findByDogsitter(String usernameDogsitter) {
        return repository.findByUsernameDogsitter(usernameDogsitter);
    }

    /**
     * Cerca un servizio offerto tramite il suo ID.
     * @param id l'ID del servizio offerto
     * @return Optional con il servizio o vuoto se non trovato
     */
    public Optional<ServizioOfferto> findById(Integer id) {
        return repository.findById(id);
    }

    /**
     * Salva o aggiorna un servizio offerto.
     * @param servizioOfferto il servizio da persistere
     * @return il servizio salvato
     */
    public ServizioOfferto save(ServizioOfferto servizioOfferto) {
        return repository.save(servizioOfferto);
    }

    /**
     * Elimina un servizio offerto tramite il suo ID.
     * @param id l'ID del servizio da rimuovere
     */
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
