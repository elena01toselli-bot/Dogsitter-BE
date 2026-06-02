package com.progettodogsitting.dogsitting_backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progettodogsitting.dogsitting_backend.model.Cane;
import com.progettodogsitting.dogsitting_backend.service.CaneService;

/**
 * Controller REST per la gestione dei Cani.
 *
 * Espone gli endpoint:
 *   GET    /api/cani/cliente/{username} → cani di un cliente
 *   POST   /api/cani                    → aggiunta nuovo cane
 *   PUT    /api/cani/{nMicrochip}       → aggiornamento cane
 *   DELETE /api/cani/{nMicrochip}       → cancellazione cane
 */
@RestController
@RequestMapping("/api/cani")
@CrossOrigin(origins = "*")
public class CaneController {

    @Autowired
    private CaneService service;

    /**
     * Recupera tutti i cani associati a un cliente.
     *
     * GET /api/cani/cliente/{usernameCliente}
     *
     * @param usernameCliente lo username del cliente proprietario
     * @return lista dei cani del cliente
     */
    @GetMapping("/cliente/{usernameCliente}")
    public List<Cane> getByCliente(@PathVariable String usernameCliente) {
        return service.findByCliente(usernameCliente);
    }

    /**
     * Registra un nuovo cane nel sistema.
     *
     * POST /api/cani
     * Body: oggetto Cane
     *
     * @param cane i dati del cane da aggiungere
     * @return il cane creato
     */
    @PostMapping
    public Cane create(@RequestBody Cane cane) {
        return service.save(cane);
    }

    /**
     * Aggiorna i dati di un cane esistente.
     *
     * PUT /api/cani/{nMicrochip}
     * Body: oggetto Cane aggiornato
     *
     * @param nMicrochip il numero di microchip del cane da aggiornare
     * @param cane       i nuovi dati del cane
     * @return 200 con il cane aggiornato, oppure 404 se non esiste
     */
    @PutMapping("/{nMicrochip}")
    public ResponseEntity<Cane> update(@PathVariable String nMicrochip,
                                       @RequestBody Cane cane) {
        if (!service.findById(nMicrochip).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cane.setNMicrochip(nMicrochip);
        return ResponseEntity.ok(service.save(cane));
    }

    /**
     * Elimina un cane tramite il numero di microchip.
     *
     * DELETE /api/cani/{nMicrochip}
     *
     * @param nMicrochip il microchip del cane da eliminare
     * @return 204 No Content se eliminato, oppure 404 se non esiste
     */
    @DeleteMapping("/{nMicrochip}")
    public ResponseEntity<Void> delete(@PathVariable String nMicrochip) {
        if (!service.findById(nMicrochip).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(nMicrochip);
        return ResponseEntity.noContent().build();
    }
}
