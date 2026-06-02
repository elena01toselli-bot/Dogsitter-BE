package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.Prenotazione;
import com.progettodogsitting.dogsitting_backend.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione delle Prenotazioni.
 * Gestisce le operazioni CRUD e la ricerca per cliente o dogsitter.
 */
@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository repository;

    /**
     * Recupera tutte le prenotazioni di un cliente.
     * @param usernameCliente lo username del cliente
     * @return lista delle prenotazioni del cliente
     */
    public List<Prenotazione> findByCliente(String usernameCliente) {
        return repository.findByUsernameCliente(usernameCliente);
    }

    /**
     * Recupera tutte le prenotazioni assegnate a un dogsitter.
     * @param usernameDogsitter lo username del dogsitter
     * @return lista delle prenotazioni del dogsitter
     */
    public List<Prenotazione> findByDogsitter(String usernameDogsitter) {
        return repository.findByUsernameDogsitter(usernameDogsitter);
    }

    /**
     * Cerca una prenotazione tramite il codice ID.
     * @param id il codice ID della prenotazione
     * @return Optional con la prenotazione o vuoto se non trovata
     */
    public Optional<Prenotazione> findById(Integer id) {
        return repository.findById(id);
    }

    /**
     * Salva o aggiorna una prenotazione.
     * @param prenotazione la prenotazione da persistere
     * @return la prenotazione salvata
     */
    public Prenotazione save(Prenotazione prenotazione) {
        return repository.save(prenotazione);
    }

    /**
     * Elimina una prenotazione tramite il codice ID.
     * @param id il codice ID della prenotazione da eliminare
     */
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
