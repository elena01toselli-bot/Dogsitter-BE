package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per l'entità Prenotazione.
 * Fornisce le operazioni CRUD sulla tabella PRENOTAZIONE.
 * La chiave primaria è codiceId (Integer).
 */
@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

    /**
     * Recupera tutte le prenotazioni di un cliente.
     *
     * @param usernameCliente lo username del cliente
     * @return lista delle prenotazioni del cliente
     */
    List<Prenotazione> findByUsernameCliente(String usernameCliente);

    /**
     * Recupera tutte le prenotazioni assegnate a un dogsitter.
     *
     * @param usernameDogsitter lo username del dogsitter
     * @return lista delle prenotazioni del dogsitter
     */
    List<Prenotazione> findByUsernameDogsitter(String usernameDogsitter);
}
