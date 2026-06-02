package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.model.ServizioOfferto;
import com.progettodogsitting.dogsitting_backend.service.ServizioOffertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Controller REST per la gestione dei Servizi Offerti dai Dogsitter.
 *
 * Espone gli endpoint:
 *   GET    /api/servizi/categorie              → lista categorie disponibili
 *   GET    /api/servizi/dogsitter/{username}   → servizi di un dogsitter
 *   POST   /api/servizi                        → aggiunta servizio
 *   PUT    /api/servizi/{id}                   → modifica servizio
 *   DELETE /api/servizi/{id}                   → rimozione servizio
 */
@RestController
@RequestMapping("/api/servizi")
@CrossOrigin(origins = "*")
public class ServizioController {

    @Autowired
    private ServizioOffertoService service;

    /**
     * Restituisce la lista delle categorie di servizio disponibili.
     * Usata dal frontend per popolare la tendina nella form del profilo dogsitter.
     *
     * GET /api/servizi/categorie
     *
     * @return lista fissa di categorie di servizio
     */
    @GetMapping("/categorie")
    public List<String> getCategorie() {
        return Arrays.asList("dogsitting", "addestramento", "toelettatura", "passeggiata", "pensione");
    }

    /**
     * Recupera tutti i servizi offerti da un determinato dogsitter.
     *
     * GET /api/servizi/dogsitter/{usernameDogsitter}
     *
     * @param usernameDogsitter lo username del dogsitter
     * @return lista dei servizi offerti dal dogsitter
     */
    @GetMapping("/dogsitter/{usernameDogsitter}")
    public List<ServizioOfferto> getByDogsitter(@PathVariable String usernameDogsitter) {
        return service.findByDogsitter(usernameDogsitter);
    }

    /**
     * Aggiunge un nuovo servizio al profilo di un dogsitter.
     *
     * POST /api/servizi
     * Body: oggetto ServizioOfferto
     *
     * @param servizio il servizio da aggiungere
     * @return il servizio creato (con ID generato)
     */
    @PostMapping
    public ServizioOfferto create(@RequestBody ServizioOfferto servizio) {
        return service.save(servizio);
    }

    /**
     * Modifica un servizio offerto esistente.
     *
     * PUT /api/servizi/{id}
     * Body: oggetto ServizioOfferto aggiornato
     *
     * @param id       l'ID del servizio da modificare
     * @param servizio i nuovi dati del servizio
     * @return 200 con il servizio aggiornato, oppure 404 se non esiste
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServizioOfferto> update(@PathVariable Integer id,
                                                  @RequestBody ServizioOfferto servizio) {
        Optional<ServizioOfferto> existing = service.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
        }

    
        servizio.setIdServizio(id);
        return ResponseEntity.ok(service.save(servizio));
    }

    /**
     * Rimuove un servizio dal profilo di un dogsitter.
     *
     * DELETE /api/servizi/{id}
     *
     * @param id l'ID del servizio da rimuovere
     * @return 204 No Content se rimosso, oppure 404 se non esiste
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
