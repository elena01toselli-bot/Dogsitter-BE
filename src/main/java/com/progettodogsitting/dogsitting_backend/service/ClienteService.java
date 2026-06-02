package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.Cliente;
import com.progettodogsitting.dogsitting_backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione dei Clienti.
 * Fornisce le operazioni CRUD sul sottoinsieme Cliente di Utente.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    /** Restituisce tutti i clienti registrati. */
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    /**
     * Cerca un cliente per username.
     * @param username la chiave primaria del cliente
     * @return Optional con il cliente o vuoto se non trovato
     */
    public Optional<Cliente> findById(String username) {
        return repository.findById(username);
    }

    /**
     * Salva o aggiorna un cliente.
     * @param cliente il cliente da persistere
     * @return il cliente salvato
     */
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    /**
     * Elimina un cliente tramite il suo username.
     * @param username lo username del cliente da eliminare
     */
    public void deleteById(String username) {
        repository.deleteById(username);
    }
}
