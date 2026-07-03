package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Lezione;
import com.progettodogsitting.dogsitting_backend.model.LezioneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository per le operazioni CRUD sulla tabella LEZIONE
@Repository
public interface LezioneRepository extends JpaRepository<Lezione, LezioneId> {
   
    // Trova tutte le lezioni di un determinato campo
    List<Lezione> findByIdNomeCampo(String nomeCampo);


    
}
