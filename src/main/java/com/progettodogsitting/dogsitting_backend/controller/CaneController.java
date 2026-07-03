package com.progettodogsitting.dogsitting_backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.progettodogsitting.dogsitting_backend.DTO.CaneDTO;
import com.progettodogsitting.dogsitting_backend.service.CaneService;

// Controller REST per la gestione dei cani
@RestController
@RequestMapping("/api/cani")
@CrossOrigin(origins = "*")
public class CaneController {

    
    @Autowired
    private CaneService service;

    // Endpoint per recuperare tutti i cani di un determinato cliente
    @GetMapping("/cliente/{usernameCliente}")
    public List<CaneDTO> getByCliente(@PathVariable String usernameCliente) {
        // Cerca e restituisce la lista di cani
        return service.findByCliente(usernameCliente);
    }

    // Endpoint per recuperare i numeri di microchip di tutti i cani di un cliente
    @GetMapping("/cliente/{usernameCliente}/microchip")
    public List<String> getMicrochipByCliente(@PathVariable String usernameCliente) {
       
        // Cerca e restituisce la lista di numeri di microchip
        return service.findMicrochipByCliente(usernameCliente);
    }

    // Endpoint per la creazione di un nuovo profilo cane
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CaneDTO cane) {
        // Salva il record
        if (service.save(cane)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }

    // Endpoint per aggiornare un cane esistente
    @PutMapping("/{nMicrochip}")
    public ResponseEntity<Void> update(@PathVariable String nMicrochip,@RequestBody CaneDTO cane) 
    {
        // Controlla l'esistenza del cane prima dell'aggiornamento
        if (!service.findById(nMicrochip).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        // Salva e ritorna i dati
        if (service.save(cane)) {

            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.status(500).build();
    }

    // Endpoint per la rimozione di un cane
    @DeleteMapping("/{nMicrochip}")
    public ResponseEntity<Void> delete(@PathVariable String nMicrochip) {
    
        // Controlla se il cane esiste
        if (!service.findById(nMicrochip).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Procede all'eliminazione fisica
        service.deleteById(nMicrochip);
        
        return ResponseEntity.noContent().build();
    }
}
