package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.DTO.RecensioneDTO;
import com.progettodogsitting.dogsitting_backend.model.RecensioneId;
import com.progettodogsitting.dogsitting_backend.service.DogsitterService;
import com.progettodogsitting.dogsitting_backend.service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/recensioni")
@CrossOrigin(origins = "*")
public class RecensioneController {


    @Autowired
    private RecensioneService service;

    @Autowired
    private DogsitterService dogsitterService;

    // Ritorna tutte le recensioni ricevute da un dogsitter
    @GetMapping("/dogsitter/{usernameDogsitter}")
    public List<RecensioneDTO> getByDogsitter(@PathVariable String usernameDogsitter) {
        
        return service.findByDogsitter(usernameDogsitter);
    }

    // Ritorna tutte le recensioni scritte da un cliente
    @GetMapping("/cliente/{usernameCliente}")
    public List<RecensioneDTO> getByCliente(@PathVariable String usernameCliente) {
        
        return service.findByCliente(usernameCliente);
    }

    // Ritorna gli username dei dogsitter disponibili (che esistono nel sistema)
    @GetMapping("/dogsitter-disponibili")
    public List<String> getDogsitterDisponibili() {
            
        return dogsitterService.findAll()        
                .stream()                        
                .map(d -> d.getUsername())       
                .collect(Collectors.toList());   
     }

    // Crea una nuova recensione
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RecensioneDTO recensione) {
        
        // Validazione dei campi fondamentali
        if (recensione.getUsernameCliente() == null || recensione.getUsernameDogsitter() == null) {
            
            return ResponseEntity.badRequest().build();
        }
        
        service.save(recensione);

        return ResponseEntity.ok().build();
    }

    // Aggiorna una recensione esistente
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody RecensioneDTO recensione) {
        
        // Controlla che il payload sia valido e contenga l'ID
        if (recensione.getUsernameCliente() == null || recensione.getUsernameDogsitter() == null) {
            
            return ResponseEntity.badRequest().build();
        }

        service.save(recensione);

        return ResponseEntity.ok().build();

    }

    // Cancella una singola recensione specificando entrambi gli username
    @DeleteMapping("/{usernameDogsitter}/{usernameCliente}")
    public ResponseEntity<Void> delete(@PathVariable String usernameDogsitter,
                                       @PathVariable String usernameCliente) {
        // Esegue la cancellazione usando la PK composta
        service.deleteById(new RecensioneId(usernameCliente, usernameDogsitter));
        return ResponseEntity.noContent().build();
    }

}
