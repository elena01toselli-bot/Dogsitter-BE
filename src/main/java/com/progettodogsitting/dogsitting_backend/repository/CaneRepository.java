package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Cane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

// Interfaccia per accesso diretto alla tabella CANE nel database
@Repository
public interface CaneRepository extends JpaRepository<Cane, String> {
    
    List<Cane> findByCliente_Username(String username); // WHERE cliente.username = ?

    @Query("SELECT c.nMicrochip FROM Cane c WHERE c.cliente.username = :username")
    List<String> findNMicrochipByCliente_Username(String username);
}
