package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Recensione;
import com.progettodogsitting.dogsitting_backend.model.RecensioneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per l'entità Recensione.
 * La chiave primaria è composta: (usernameCliente, usernameDogsitter).
 */
@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, RecensioneId> {

    /**
     * Recupera tutte le recensioni ricevute da un dogsitter.
     *
     * @param usernameDogsitter lo username del dogsitter
     * @return lista delle recensioni
     */
    List<Recensione> findByIdUsernameDogsitter(String usernameDogsitter);

    /**
     * Recupera tutte le recensioni scritte da un cliente.
     *
     * @param usernameCliente lo username del cliente
     * @return lista delle recensioni scritte dal cliente
     */
    List<Recensione> findByIdUsernameCliente(String usernameCliente);

    /**
     * Elimina tutte le recensioni scritte da un cliente.
     * Usato quando si elimina un cliente o si cancellano tutte le sue recensioni.
     *
     * @param usernameCliente lo username del cliente
     */
    void deleteByIdUsernameCliente(String usernameCliente);

    /**
     * Recupera tutti gli username distinti dei dogsitter che hanno almeno una recensione.
     * Usato per popolare la select nel form "aggiungi recensione" del frontend.
     *
     * @return lista di username dogsitter disponibili
     */
    List<String> findDistinctIdUsernameDogsitterBy();
}
