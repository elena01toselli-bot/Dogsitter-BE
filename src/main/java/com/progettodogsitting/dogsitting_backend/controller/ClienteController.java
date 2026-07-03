package com.progettodogsitting.dogsitting_backend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.progettodogsitting.dogsitting_backend.DTO.ClientDTO;
import com.progettodogsitting.dogsitting_backend.service.ClienteService;

// Controller REST per la gestione degli utenti di tipo Cliente
@RestController
@RequestMapping("/api/clienti")
@CrossOrigin(origins = "*")
public class ClienteController {

    
    @Autowired
    private ClienteService service;

    // Metodo per ottenere tutti i clienti registrati
    @GetMapping
    public List<ClientDTO> getAll() {
    
        return service.findAll();
    }

    // Metodo per trovare un cliente in base allo username
    @GetMapping("/{username}")
    public ResponseEntity<ClientDTO> getById(@PathVariable String username) {
    
        // Ricerca il cliente
        Optional<ClientDTO> cliente = service.findById(username);
        // Restituisce 200 OK se trovato, altrimenti 404
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Metodo per aggiornare i dati di un cliente
    @PutMapping("/{username}")
    public ResponseEntity<ClientDTO> update(@PathVariable String username,
                                          @RequestBody ClientDTO cliente) {
        // Controlla se il record esiste
        if (!service.findById(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        

        // Salva le modifiche
        return ResponseEntity.ok(service.save(cliente));
    }

    // Metodo per rimuovere un cliente dal sistema
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        // Verifica se l'utente è presente
        if (!service.findById(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Cancella il cliente
        service.deleteById(username);
        return ResponseEntity.noContent().build();
    }
}
