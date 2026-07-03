package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Recensione;
import com.progettodogsitting.dogsitting_backend.model.RecensioneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Repository per le operazioni CRUD sulla tabella delle recensioni
@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, RecensioneId> {
    
    // Filtra per username del dogsitter all'interno dell'ID composto
    List<Recensione> findByIdUsernameDogsitter(String usernameDogsitter);
    
    // Filtra per username del cliente all'interno dell'ID composto
    List<Recensione> findByIdUsernameCliente(String usernameCliente);
    
    // Metodo di eliminazione per cancellare tutte le recensioni di un dato cliente
    void deleteByIdUsernameCliente(String usernameCliente);
    
    // Ritorna gli username distinti dei dogsitter presenti in tabella
    List<String> findDistinctIdUsernameDogsitterBy();
}
