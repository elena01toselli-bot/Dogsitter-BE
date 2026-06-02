package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.model.Dogsitter;
import com.progettodogsitting.dogsitting_backend.service.DogsitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST per la gestione dei Dogsitter.
 *
 * Espone gli endpoint:
 *   GET    /api/dogsitter            → lista tutti i dogsitter
 *   GET    /api/dogsitter/{username} → dettaglio dogsitter
 *   PUT    /api/dogsitter/{username} → aggiornamento profilo dogsitter
 *   DELETE /api/dogsitter/{username} → cancellazione dogsitter
 */
@RestController
@RequestMapping("/api/dogsitter")
@CrossOrigin(origins = "*")
public class DogsitterController {

    @Autowired
    private DogsitterService service;

    /**
     * Restituisce la lista di tutti i dogsitter registrati.
     *
     * GET /api/dogsitter
     *
     * @return lista di dogsitter
     */
    @GetMapping
    public List<Dogsitter> getAll() {
        return service.findAll();
    }

    /**
     * Restituisce i dati di un dogsitter specifico tramite username.
     *
     * GET /api/dogsitter/{username}
     *
     * @param username lo username del dogsitter da cercare
     * @return 200 con il dogsitter, oppure 404 se non esiste
     */
    @GetMapping("/{username}")
    public ResponseEntity<Dogsitter> getById(@PathVariable String username) {
        Optional<Dogsitter> dogsitter = service.findById(username);
        return dogsitter.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna il profilo di un dogsitter esistente.
     *
     * PUT /api/dogsitter/{username}
     * Body: oggetto Dogsitter aggiornato
     *
     * @param username  lo username del dogsitter da aggiornare
     * @param dogsitter i nuovi dati del dogsitter
     * @return 200 con il dogsitter aggiornato, oppure 404 se non esiste
     */
    @PutMapping("/{username}")
    public ResponseEntity<Dogsitter> update(@PathVariable String username,
                                            @RequestBody Dogsitter dogsitter) {
        if (!service.findById(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        dogsitter.setUsername(username);
        return ResponseEntity.ok(service.save(dogsitter));
    }

    /**
     * Elimina un dogsitter tramite il suo username.
     *
     * DELETE /api/dogsitter/{username}
     *
     * @param username lo username del dogsitter da eliminare
     * @return 204 No Content se eliminato, oppure 404 se non esiste
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        if (!service.findById(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(username);
        return ResponseEntity.noContent().build();
    }
}
