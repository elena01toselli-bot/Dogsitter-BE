package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.model.Prenotazione;
import com.progettodogsitting.dogsitting_backend.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST per la gestione delle Prenotazioni.
 *
 * Espone gli endpoint:
 *   GET    /api/prenotazioni/cliente/{username}   → prenotazioni di un cliente
 *   GET    /api/prenotazioni/dogsitter/{username} → prenotazioni di un dogsitter
 *   GET    /api/prenotazioni/{id}                 → dettaglio prenotazione
 *   POST   /api/prenotazioni                      → nuova prenotazione
 *   PUT    /api/prenotazioni/{id}                 → aggiornamento prenotazione
 *   DELETE /api/prenotazioni/{id}                 → cancellazione prenotazione
 */
@RestController
@RequestMapping("/api/prenotazioni")
@CrossOrigin(origins = "*")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService service;

    /**
     * Recupera tutte le prenotazioni effettuate da un cliente.
     *
     * GET /api/prenotazioni/cliente/{usernameCliente}
     *
     * @param usernameCliente lo username del cliente
     * @return lista di prenotazioni del cliente
     */
    @GetMapping("/cliente/{usernameCliente}")
    public List<Prenotazione> getByCliente(@PathVariable String usernameCliente) {
        return service.findByCliente(usernameCliente);
    }

    /**
     * Recupera tutte le prenotazioni assegnate a un dogsitter.
     *
     * GET /api/prenotazioni/dogsitter/{usernameDogsitter}
     *
     * @param usernameDogsitter lo username del dogsitter
     * @return lista di prenotazioni del dogsitter
     */
    @GetMapping("/dogsitter/{usernameDogsitter}")
    public List<Prenotazione> getByDogsitter(@PathVariable String usernameDogsitter) {
        return service.findByDogsitter(usernameDogsitter);
    }

    /**
     * Recupera una prenotazione specifica tramite il suo codice ID.
     *
     * GET /api/prenotazioni/{id}
     *
     * @param id il codice ID della prenotazione
     * @return 200 con la prenotazione, oppure 404 se non esiste
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prenotazione> getById(@PathVariable Integer id) {
        Optional<Prenotazione> pren = service.findById(id);
        return pren.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea una nuova prenotazione.
     *
     * POST /api/prenotazioni
     * Body: oggetto Prenotazione
     *
     * @param prenotazione i dati della nuova prenotazione
     * @return la prenotazione creata (con codiceId generato)
     */
    @PostMapping
    public Prenotazione create(@RequestBody Prenotazione prenotazione) {
        return service.save(prenotazione);
    }

    /**
     * Aggiorna una prenotazione esistente.
     *
     * PUT /api/prenotazioni/{id}
     * Body: oggetto Prenotazione aggiornato
     *
     * @param id           il codice ID della prenotazione da aggiornare
     * @param prenotazione i nuovi dati della prenotazione
     * @return 200 con la prenotazione aggiornata, oppure 404 se non esiste
     */
    @PutMapping("/{id}")
    public ResponseEntity<Prenotazione> update(@PathVariable Integer id,
                                               @RequestBody Prenotazione prenotazione) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        prenotazione.setCodiceId(id);
        return ResponseEntity.ok(service.save(prenotazione));
    }

    /**
     * Elimina una prenotazione tramite il codice ID.
     *
     * DELETE /api/prenotazioni/{id}
     *
     * @param id il codice ID della prenotazione da eliminare
     * @return 204 No Content se eliminata, oppure 404 se non esiste
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
