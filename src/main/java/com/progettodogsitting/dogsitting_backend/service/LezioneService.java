package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.Lezione;
import com.progettodogsitting.dogsitting_backend.model.LezioneId;
import com.progettodogsitting.dogsitting_backend.repository.LezioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione delle Lezioni di addestramento.
 * Gestisce le operazioni CRUD e la ricerca per campo di addestramento.
 */
@Service
public class LezioneService {

    @Autowired
    private LezioneRepository repository;

    /**
     * Recupera tutte le lezioni associate a un campo di addestramento.
     * @param nomeCampo il nome del campo
     * @return lista delle lezioni del campo
     */
    public List<Lezione> findByCampo(String nomeCampo) {
        return repository.findByIdNomeCampo(nomeCampo);
    }

    /**
     * Cerca una lezione tramite la chiave composta (nomeCampo, ora, data).
     * @param id la chiave primaria composta
     * @return Optional con la lezione o vuoto se non trovata
     */
    public Optional<Lezione> findById(LezioneId id) {
        return repository.findById(id);
    }

    /**
     * Salva o aggiorna una lezione.
     * @param lezione la lezione da persistere
     * @return la lezione salvata
     */
    public Lezione save(Lezione lezione) {
        return repository.save(lezione);
    }

    /**
     * Elimina una lezione tramite la chiave composta.
     * @param id la chiave primaria composta della lezione
     */
    public void deleteById(LezioneId id) {
        repository.deleteById(id);
    }
}
