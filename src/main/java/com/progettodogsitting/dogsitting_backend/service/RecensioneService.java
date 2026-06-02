package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.Recensione;
import com.progettodogsitting.dogsitting_backend.model.RecensioneId;
import com.progettodogsitting.dogsitting_backend.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service per la gestione delle Recensioni.
 * Gestisce le operazioni CRUD e le query filtrate per cliente o dogsitter.
 */
@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository repository;

    /**
     * Recupera tutte le recensioni ricevute da un dogsitter.
     * @param usernameDogsitter lo username del dogsitter
     * @return lista delle recensioni del dogsitter
     */
    public List<Recensione> findByDogsitter(String usernameDogsitter) {
        return repository.findByIdUsernameDogsitter(usernameDogsitter);
    }

    /**
     * Recupera tutte le recensioni scritte da un cliente.
     * @param usernameCliente lo username del cliente
     * @return lista delle recensioni del cliente
     */
    public List<Recensione> findByCliente(String usernameCliente) {
        return repository.findByIdUsernameCliente(usernameCliente);
    }

    /**
     * Salva o aggiorna una recensione.
     * @param recensione la recensione da persistere
     * @return la recensione salvata
     */
    public Recensione save(Recensione recensione) {
        return repository.save(recensione);
    }

    /**
     * Elimina una recensione tramite la chiave composta (usernameCliente, usernameDogsitter).
     * @param id la chiave primaria composta
     */
    public void deleteById(RecensioneId id) {
        repository.deleteById(id);
    }

    /**
     * Elimina tutte le recensioni scritte da un cliente.
     * Usato quando si rimuove un account cliente.
     * @param usernameCliente lo username del cliente
     */
    @Transactional
    public void deleteAllByCliente(String usernameCliente) {
        repository.deleteByIdUsernameCliente(usernameCliente);
    }

    /**
     * Recupera la lista degli username di tutti i dogsitter che hanno almeno una recensione.
     * Usato dal frontend per popolare la select nel form "aggiungi recensione".
     * @return lista di username dogsitter disponibili
     */
    public List<String> findDogsitterDisponibili() {
        return repository.findDistinctIdUsernameDogsitterBy();
    }
}
