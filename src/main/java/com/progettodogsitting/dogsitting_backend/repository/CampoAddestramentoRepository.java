package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.CampoAddestramento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interfaccia repository per CampoAddestramento
@Repository
public interface CampoAddestramentoRepository extends JpaRepository<CampoAddestramento, String> {
    
}
