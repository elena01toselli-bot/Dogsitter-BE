package com.progettodogsitting.dogsitting_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.progettodogsitting.dogsitting_backend.model.CampoAddestramento;
import com.progettodogsitting.dogsitting_backend.service.CampoAddestramentoService;


@RestController
@RequestMapping("/api/campi")
@CrossOrigin(origins = "*")
public class CampoAddestramentoController {


    @Autowired
    private CampoAddestramentoService service;

    // Gestisce le richieste GET alla root /api/campi
    @GetMapping
    public List<CampoAddestramento> getAll() {

        // Ritorna la lista completa dei campi
        return service.findAll();
    }

    // Gestisce le richieste GET per un campo specifico tramite il suo nome
    @GetMapping("/{nome}")
    public ResponseEntity<CampoAddestramento> getByNome(@PathVariable String nome) {
        
        // Cerca il campo nel database
        Optional<CampoAddestramento> campo = service.findById(nome);

        // Se lo trova ritorna 200 OK, altrimenti 404 Not Found
        return campo.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Gestisce le richieste POST per creare un nuovo campo
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CampoAddestramento campo) {
        
        // Salva il nuovo campo 
        if(service.findById(campo.getNome()).isPresent()) {

            return ResponseEntity.status(500).body(null);
        }
        service.save(campo);

        return ResponseEntity.noContent().build();

    }

    // Gestisce le richieste PUT per aggiornare un campo esistente
    @PutMapping("/{nome}")
    public ResponseEntity<Void> update(@PathVariable String nome,
                                                     @RequestBody CampoAddestramento campo) {
        // Verifica se il campo esiste prima di aggiornarlo
        if (!service.findById(nome).isPresent()) {
            // Se non esiste, ritorna 404 Not Found
            return ResponseEntity.notFound().build();
        }

        service.save(campo);        

        // Salva e ritorna 200 OK
        return ResponseEntity.noContent().build();
    }

    // Gestisce le richieste DELETE per rimuovere un campo
    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> delete(@PathVariable String nome) {
        // Verifica l'esistenza prima di cancellare
        if (!service.findById(nome).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Elimina il record dal DB
        service.deleteById(nome);
        // Ritorna 204 No Content
        return ResponseEntity.noContent().build();
    }
}
