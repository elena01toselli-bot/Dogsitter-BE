package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.UtenteDTO;
import com.progettodogsitting.dogsitting_backend.model.Cliente;
import com.progettodogsitting.dogsitting_backend.model.Dogsitter;
import com.progettodogsitting.dogsitting_backend.model.Utente;
import com.progettodogsitting.dogsitting_backend.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione degli Utenti.
 * Contiene la logica di business per registrazione, login, ricerca,
 * aggiornamento e cancellazione utenti.
 */
@Service
public class UtenteService {

    @Autowired
    private UtenteRepository repository;

    /** Restituisce tutti gli utenti presenti nel database. */
    public List<Utente> findAll() {
        return repository.findAll();
    }

    /**
     * Cerca un utente tramite il suo username (chiave primaria).
     * @param username la chiave primaria
     * @return Optional con l'utente o vuoto se non trovato
     */
    public Optional<Utente> findById(String username) {
        return repository.findById(username);
    }

    /**
     * Salva un nuovo utente o aggiorna uno esistente.
     * @param utente l'utente da persistere
     * @return l'utente salvato
     */
    public Utente save(Utente utente) {
        return repository.save(utente);
    }

    /**
     * Elimina un utente dal database tramite lo username.
     * @param username lo username dell'utente da eliminare
     */
    public void deleteById(String username) {
        repository.deleteById(username);
    }

    /**
     * Verifica le credenziali di login.
     * @param username lo username inserito
     * @param password la password inserita
     * @return Optional con l'utente se autenticato, vuoto se le credenziali sono errate
     */
    public UtenteDTO login(String username, String password) {

        Optional<Utente> utente=repository.findByUsernameAndPassword(username, password);

        if(utente.isPresent()){

            //creo il DTO con anche il ruolo (cliente o dogsitter) per restituirlo al frontend
            UtenteDTO utenteDTO = new UtenteDTO();

            utenteDTO.setUsername(utente.get().getUsername());
            utenteDTO.setRuolo(this.getRuolo(username));

            return utenteDTO;
        }
        return null;
    }
    /**
     * Cerca utenti per username — usato dal frontend come fallback di ricerca.
     * @param username lo username da filtrare
     * @return lista di utenti con quel username (di norma 0 o 1)
     */
    public List<Utente> findAllByUsername(String username) {
        return repository.findAllByUsername(username);
    }

    public String getRuolo(String username) {

        Optional<Utente> utente = repository.findById(username);

        if (utente.isPresent()) {

            Utente u = utente.get();
            
            if(u instanceof Cliente) {
                return "cliente";
            } else 
                if (u instanceof Dogsitter) {
                return "dogsitter";
                }

        }
        return "ruolo sconosciuto";
    }
}
