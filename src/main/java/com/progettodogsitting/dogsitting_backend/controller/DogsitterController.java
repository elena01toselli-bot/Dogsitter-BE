package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.DTO.DogsitterDTO;
import com.progettodogsitting.dogsitting_backend.service.DogsitterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

// Controller REST per la gestione degli utenti di tipo Dogsitter
@RestController
@RequestMapping("/api/dogsitter")
@CrossOrigin(origins = "*")
public class DogsitterController {

    
    @Autowired
    private DogsitterService service;  

    // Metodo per ritornare la lista completa di tutti i dogsitter
    @GetMapping
    public List<DogsitterDTO> getAll() {

        return service.findAll();
    }

    // Metodo per ritornare il profilo di un singolo dogsitter, cercandolo per username
    @GetMapping("/{username}")
    public ResponseEntity<DogsitterDTO> getById(@PathVariable String username) {

        // Cerca sul DB
        Optional<DogsitterDTO> dogsitter = service.findById(username);

        // Risponde con un 200 OK e il JSON, oppure 404 se non esiste
        return dogsitter.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Aggiorna le informazioni di un dogsitter
    @PutMapping("/{username}")
    public ResponseEntity<DogsitterDTO> update(@PathVariable String username,
                                            @RequestBody DogsitterDTO dogsitter) {
        // Verifica esistenza
        if (!service.findById(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        
        // Salva le modifiche
        return ResponseEntity.ok(service.save(dogsitter));
    }

    // Elimina un dogsitter
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        // Controlla se c'è
        if (!service.findById(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Cancella
        service.deleteById(username);
        return ResponseEntity.noContent().build();
    }
}
