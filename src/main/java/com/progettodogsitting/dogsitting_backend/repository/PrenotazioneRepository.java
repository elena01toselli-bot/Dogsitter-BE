package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Mapping sulla tabella PRENOTAZIONE
@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    
    // Trova la prenotazione partendo dal suo id di tipo stringa
    Optional<Prenotazione> findByCodiceId(Integer codiceId);
    
    
    // Spring naviga: Prenotazione → Cane → Cliente → username
    List<Prenotazione> findByCane_Cliente_Username(String username);

        //SELECT p.* 
        // FROM prenotazione p
        // JOIN cane c ON p.n_microchip = c.n_microchip
        // JOIN cliente cl ON c.username_c = cl.username
        // WHERE cl.username = 'mario99'

    
        // Query manuale perché la relazione è inversa
    @Query("SELECT p FROM Prenotazione p " +
           "JOIN EsecuzioneServizio e ON e.prenotazione = p " +
           "WHERE e.dogsitter.username = :username")
        // SQL equivalente sarebbe:SELECT p.* 
        // FROM prenotazione p
        // JOIN esecuzione_servizio e ON e.codice_id_pren = p.codice_id
        // WHERE e.username_d = 'mario99'
    List<Prenotazione> findByDogsitterUsername(@Param("username") String username);
}
