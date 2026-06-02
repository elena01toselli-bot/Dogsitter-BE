package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.Cane;
import com.progettodogsitting.dogsitting_backend.repository.CaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione dei Cani.
 * Gestisce le operazioni CRUD sui cani e la ricerca per cliente.
 */
@Service
public class CaneService {

    @Autowired
    private CaneRepository repository;

    /**
     * Recupera tutti i cani di un determinato cliente.
     * @param usernameCliente lo username del cliente
     * @return lista dei cani del cliente
     */
    public List<Cane> findByCliente(String usernameCliente) {
        return repository.findByUsernameCliente(usernameCliente);
    }

    /**
     * Cerca un cane tramite il numero di microchip (chiave primaria).
     * @param nMicrochip il numero di microchip
     * @return Optional con il cane o vuoto se non trovato
     */
    public Optional<Cane> findById(String nMicrochip) {
        return repository.findById(nMicrochip);
    }

    /**
     * Salva o aggiorna un cane.
     * @param cane il cane da persistere
     * @return il cane salvato
     */
    public Cane save(Cane cane) {
        return repository.save(cane);
    }

    /**
     * Elimina un cane tramite il numero di microchip.
     * @param nMicrochip il microchip del cane da eliminare
     */
    public void deleteById(String nMicrochip) {
        repository.deleteById(nMicrochip);
    }
}
