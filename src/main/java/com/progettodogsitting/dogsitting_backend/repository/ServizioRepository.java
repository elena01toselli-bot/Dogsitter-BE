package com.progettodogsitting.dogsitting_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progettodogsitting.dogsitting_backend.model.Servizio;

@Repository
public interface ServizioRepository extends JpaRepository<Servizio, Integer>{
      
}
