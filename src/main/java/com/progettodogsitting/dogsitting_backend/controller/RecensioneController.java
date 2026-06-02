package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.model.Recensione;
import com.progettodogsitting.dogsitting_backend.model.RecensioneId;
import com.progettodogsitting.dogsitting_backend.service.DogsitterService;
import com.progettodogsitting.dogsitting_backend.service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller REST per la gestione delle Recensioni.
 *
 * Espone gli endpoint:
 *   GET    /api/recensioni/dogsitter/{username}          → recensioni di un dogsitter
 *   GET    /api/recensioni/cliente/{username}            → recensioni di un cliente
 *   GET    /api/recensioni/dogsitter-disponibili         → username dei dogsitter disponibili
 *   POST   /api/recensioni                               → aggiunta recensione
 *   PUT    /api/recensioni                               → modifica recensione
 *   DELETE /api/recensioni/{usernameDogsitter}/{usernameCliente} → cancellazione singola
 *   DELETE /api/recensioni/cliente/{usernameCliente}    → cancellazione tutte di un cliente
 */
@RestController
@RequestMapping("/api/recensioni")
@CrossOrigin(origins = "*")
public class RecensioneController {

    @Autowired
    private RecensioneService service;

    @Autowired
    private DogsitterService dogsitterService;

    /**
     * Recupera tutte le recensioni ricevute da un dogsitter specifico.
     *
     * GET /api/recensioni/dogsitter/{usernameDogsitter}
     *
     * @param usernameDogsitter lo username del dogsitter
     * @return lista di recensioni
     */
    @GetMapping("/dogsitter/{usernameDogsitter}")
    public List<Recensione> getByDogsitter(@PathVariable String usernameDogsitter) {
        return service.findByDogsitter(usernameDogsitter);
    }

    /**
     * Recupera tutte le recensioni scritte da un cliente.
     *
     * GET /api/recensioni/cliente/{usernameCliente}
     *
     * @param usernameCliente lo username del cliente
     * @return lista di recensioni
     */
    @GetMapping("/cliente/{usernameCliente}")
    public List<Recensione> getByCliente(@PathVariable String usernameCliente) {
        return service.findByCliente(usernameCliente);
    }

    /**
     * Restituisce la lista degli username di tutti i dogsitter registrati.
     * Usato dal frontend per popolare la select "scegli dogsitter" nel form recensione.
     *
     * GET /api/recensioni/dogsitter-disponibili
     *
     * @return lista di username dogsitter
     */
    @GetMapping("/dogsitter-disponibili")
    public List<String> getDogsitterDisponibili() {
        // Restituisce tutti i dogsitter registrati (non solo quelli con recensioni)
        return dogsitterService.findAll()
                .stream()
                .map(d -> d.getUsername())
                .collect(Collectors.toList());
    }

    /**
     * Aggiunge una nuova recensione.
     * L'id composta viene costruita dal body ricevuto.
     *
     * POST /api/recensioni
     * Body: oggetto Recensione
     *
     * @param recensione la recensione da aggiungere
     * @return la recensione creata
     */
    @PostMapping
    public Recensione create(@RequestBody Recensione recensione) {
        // Costruiamo l'id composta dai campi del body
        recensione.setId(new RecensioneId(
            recensione.getUsernameCliente(),
            recensione.getUsernameDogsitter()
        ));
        return service.save(recensione);
    }

    /**
     * Aggiorna una recensione esistente (es. voto o commento).
     *
     * PUT /api/recensioni
     * Body: oggetto Recensione aggiornato
     *
     * @param recensione la recensione con i dati aggiornati
     * @return 200 con la recensione aggiornata, oppure 404 se non esiste
     */
    @PutMapping
    public ResponseEntity<Recensione> update(@RequestBody Recensione recensione) {
        RecensioneId id = new RecensioneId(
            recensione.getUsernameCliente(),
            recensione.getUsernameDogsitter()
        );
        if (!service.findByCliente(recensione.getUsernameCliente()).isEmpty()) {
            recensione.setId(id);
            return ResponseEntity.ok(service.save(recensione));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Elimina una recensione specifica tramite i due username (cliente + dogsitter).
     *
     * DELETE /api/recensioni/{usernameDogsitter}/{usernameCliente}
     *
     * @param usernameDogsitter lo username del dogsitter
     * @param usernameCliente   lo username del cliente
     * @return 204 No Content
     */
    @DeleteMapping("/{usernameDogsitter}/{usernameCliente}")
    public ResponseEntity<Void> delete(@PathVariable String usernameDogsitter,
                                       @PathVariable String usernameCliente) {
        service.deleteById(new RecensioneId(usernameCliente, usernameDogsitter));
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina tutte le recensioni scritte da un cliente.
     * Usato quando il cliente cancella il proprio account.
     *
     * DELETE /api/recensioni/cliente/{usernameCliente}
     *
     * @param usernameCliente lo username del cliente
     * @return 204 No Content
     */
    @DeleteMapping("/cliente/{usernameCliente}")
    public ResponseEntity<Void> deleteAllByCliente(@PathVariable String usernameCliente) {
        service.deleteAllByCliente(usernameCliente);
        return ResponseEntity.noContent().build();
    }
}
