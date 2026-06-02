package com.progettodogsitting.dogsitting_backend.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progettodogsitting.dogsitting_backend.model.Cliente;
import com.progettodogsitting.dogsitting_backend.service.ClienteService;

/**
 * Controller REST per la gestione dei Clienti.
 *
 * Espone gli endpoint:
 *   GET    /api/clienti            → lista tutti i clienti
 *   GET    /api/clienti/{username} → dettaglio cliente
 *   PUT    /api/clienti/{username} → aggiornamento profilo cliente
 *   DELETE /api/clienti/{username} → cancellazione cliente
 *
 * Nota: la creazione del cliente avviene tramite UtenteController (POST /api/utenti).
 */
@RestController
@RequestMapping("/api/clienti")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService service;

    /**
     * Restituisce la lista di tutti i clienti registrati.
     *
     * GET /api/clienti
     *
     * @return lista di clienti
     */
    @GetMapping
    public List<Cliente> getAll() {
        return service.findAll();
    }

    /**
     * Restituisce i dati di un cliente specifico tramite username.
     *
     * GET /api/clienti/{username}
     *
     * @param username lo username del cliente da cercare
     * @return 200 con il cliente, oppure 404 se non esiste
     */
    @GetMapping("/{username}")
    public ResponseEntity<Cliente> getById(@PathVariable String username) {
        Optional<Cliente> cliente = service.findById(username);
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna il profilo di un cliente esistente.
     *
     * PUT /api/clienti/{username}
     * Body: oggetto Cliente aggiornato
     *
     * @param username lo username del cliente da aggiornare
     * @param cliente  i nuovi dati del cliente
     * @return 200 con il cliente aggiornato, oppure 404 se non esiste
     */
    @PutMapping("/{username}")
    public ResponseEntity<Cliente> update(@PathVariable String username,
                                          @RequestBody Cliente cliente) {
        if (!service.findById(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cliente.setUsername(username);
        return ResponseEntity.ok(service.save(cliente));
    }

    /**
     * Elimina un cliente tramite il suo username.
     *
     * DELETE /api/clienti/{username}
     *
     * @param username lo username del cliente da eliminare
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
