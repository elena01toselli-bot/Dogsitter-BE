package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.Dogsitter;
import com.progettodogsitting.dogsitting_backend.repository.DogsitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione dei Dogsitter.
 * Fornisce le operazioni CRUD per il ruolo Dogsitter.
 */
@Service
public class DogsitterService {

    @Autowired
    private DogsitterRepository repository;

    /** Restituisce tutti i dogsitter registrati. */
    public List<Dogsitter> findAll() {
        return repository.findAll();
    }

    /**
     * Cerca un dogsitter per username.
     * @param username la chiave primaria
     * @return Optional con il dogsitter o vuoto se non trovato
     */
    public Optional<Dogsitter> findById(String username) {
        return repository.findById(username);
    }

    /**
     * Salva o aggiorna un dogsitter.
     * @param dogsitter il dogsitter da persistere
     * @return il dogsitter salvato
     */
    public Dogsitter save(Dogsitter dogsitter) {
        return repository.save(dogsitter);
    }

    /**
     * Elimina un dogsitter tramite il suo username.
     * @param username lo username del dogsitter da eliminare
     */
    public void deleteById(String username) {
        repository.deleteById(username);
    }
}
