package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.CampoAddestramento;
import com.progettodogsitting.dogsitting_backend.repository.CampoAddestramentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione dei Campi di Addestramento.
 * Fornisce le operazioni CRUD sui campi, gestiti dall'amministratore.
 */
@Service
public class CampoAddestramentoService {

    @Autowired
    private CampoAddestramentoRepository repository;

    /** Restituisce tutti i campi di addestramento registrati. */
    public List<CampoAddestramento> findAll() {
        return repository.findAll();
    }

    /**
     * Cerca un campo tramite il nome (chiave primaria).
     * @param nome il nome del campo
     * @return Optional con il campo o vuoto se non trovato
     */
    public Optional<CampoAddestramento> findById(String nome) {
        return repository.findById(nome);
    }

    /**
     * Salva o aggiorna un campo di addestramento.
     * @param campo il campo da persistere
     * @return il campo salvato
     */
    public CampoAddestramento save(CampoAddestramento campo) {
        return repository.save(campo);
    }

    /**
     * Elimina un campo di addestramento tramite il nome.
     * @param nome il nome del campo da eliminare
     */
    public void deleteById(String nome) {
        repository.deleteById(nome);
    }
}
