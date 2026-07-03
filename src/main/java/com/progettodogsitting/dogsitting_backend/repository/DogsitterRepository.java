package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Dogsitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interfaccia per il mapping sulla tabella DOG_SITTER
@Repository
public interface DogsitterRepository extends JpaRepository<Dogsitter, String> {
    // I metodi CRUD base sono tutti forniti dall'estensione di JpaRepository
    
    // dogsitterRepository.findById("mario99");    // cerca per PK
    // dogsitterRepository.findAll();              // tutti i dogsitter
    // dogsitterRepository.existsById("mario99"); // esiste?
    // dogsitterRepository.count();               // quanti sono?
    
    // dogsitterRepository.save(dogsitter);       // INSERT o UPDATE
    // quando lo fai scrive queste in automatico:
    /*INSERT INTO utente (username, ...) VALUES ('mario', ...)
    INSERT INTO dog_sitter (username, max_cani) VALUES ('mario', 3)
    INSERT INTO giorni_dog_sitter (username, giorno) VALUES ('mario', 'lunedì')
    INSERT INTO giorni_dog_sitter (username, giorno) VALUES ('mario', 'martedì')
    INSERT INTO taglie_cani (username, taglia) VALUES ('mario', 'piccola')
    INSERT INTO taglie_cani (username, taglia) VALUES ('mario', 'media') */
     



    // dogsitterRepository.saveAll(lista);        // salva una lista
    
    // dogsitterRepository.deleteById("mario99");
    // dogsitterRepository.delete(dogsitter);
    // dogsitterRepository.deleteAll();

    

}   
