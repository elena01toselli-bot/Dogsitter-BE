package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Offre;
import com.progettodogsitting.dogsitting_backend.model.OffreId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Interfaccia per il CRUD sulla tabella OFFRE
@Repository
public interface OffreRepository extends JpaRepository<Offre, OffreId> {
    
    // Trova tutti i servizi proposti da uno specifico dogsitter
    List<Offre> findByDogsitter_Username(String username);
}
