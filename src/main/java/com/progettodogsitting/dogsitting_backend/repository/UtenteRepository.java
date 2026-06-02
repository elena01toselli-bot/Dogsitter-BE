package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository per l'entità Utente.
 * Estende JpaRepository per ottenere gratuitamente le operazioni CRUD base.
 * La chiave primaria è lo username (String).
 */
@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {

    /**
     * Cerca un utente per username e password.
     * Usato per il login: verifica che le credenziali corrispondano.
     *
     * @param username lo username da cercare
     * @param password la password (in chiaro; in produzione usare hash)
     * @return l'utente trovato, se le credenziali sono corrette
     */
    Optional<Utente> findByUsernameAndPassword(String username, String password);

    /**
     * Cerca tutti gli utenti con un determinato username (per query filtrate).
     * Usato dal frontend come fallback per trovare utenti per username.
     *
     * @param username lo username da filtrare
     * @return lista di utenti corrispondenti (di norma 0 o 1)
     */
    List<Utente> findAllByUsername(String username);
}
