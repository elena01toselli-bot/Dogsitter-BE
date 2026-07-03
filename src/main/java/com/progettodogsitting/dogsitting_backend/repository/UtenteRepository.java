package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Accesso ai dati degli Utenti nel DB
@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
    
    
    // Trova gli utenti filtrandoli per il loro username esatto
    Optional<Utente> findByUsername(String username);

    
}
