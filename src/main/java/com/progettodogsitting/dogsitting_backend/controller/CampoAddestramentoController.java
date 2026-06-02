package com.progettodogsitting.dogsitting_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progettodogsitting.dogsitting_backend.model.CampoAddestramento;
import com.progettodogsitting.dogsitting_backend.service.CampoAddestramentoService;

/**
 * Controller REST per la gestione dei Campi di Addestramento.
 *
 * Espone gli endpoint:
 *   GET    /api/campi        → lista tutti i campi
 *   GET    /api/campi/{nome} → dettaglio campo
 *   POST   /api/campi        → creazione campo (admin)
 *   PUT    /api/campi/{nome} → aggiornamento campo (admin)
 *   DELETE /api/campi/{nome} → cancellazione campo (admin)
 */
@RestController
@RequestMapping("/api/campi")
@CrossOrigin(origins = "*")
public class CampoAddestramentoController {

    @Autowired
    private CampoAddestramentoService service;

    /**
     * Restituisce tutti i campi di addestramento registrati.
     *
     * GET /api/campi
     * 
     */
    @GetMapping
    public List<CampoAddestramento> getAll() {
        return service.findAll();
    }

    /**
     * Restituisce un campo specifico tramite il nome.
     *
     * GET /api/campi/{nome}
     *
     * @param nome il nome del campo da cercare
     * @return 200 con il campo, oppure 404 se non esiste
     */
    @GetMapping("/{nome}")
    public ResponseEntity<CampoAddestramento> getByNome(@PathVariable String nome) {
        Optional<CampoAddestramento> campo = service.findById(nome);
        return campo.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuovo campo di addestramento (operazione riservata all'amministratore).
     *
     * POST /api/campi
     * Body: oggetto CampoAddestramento
     *
     * @param campo i dati del nuovo campo
     * @return il campo creato
     */
    @PostMapping
    public CampoAddestramento create(@RequestBody CampoAddestramento campo) {
        return service.save(campo);
    }

    /**
     * Aggiorna i dati di un campo esistente.
     *
     * PUT /api/campi/{nome}
     * Body: oggetto CampoAddestramento aggiornato
     *
     * @param nome  il nome del campo da aggiornare
     * @param campo i nuovi dati del campo
     * @return 200 con il campo aggiornato, oppure 404 se non esiste
     */
    @PutMapping("/{nome}")
    public ResponseEntity<CampoAddestramento> update(@PathVariable String nome,
                                                     @RequestBody CampoAddestramento campo) {
        if (!service.findById(nome).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        campo.setNome(nome);
        return ResponseEntity.ok(service.save(campo));
    }

    /**
     * Elimina un campo di addestramento tramite il nome.
     *
     * DELETE /api/campi/{nome}
     *
     * @param nome il nome del campo da eliminare
     * @return 204 No Content se eliminato, oppure 404 se non esiste
     */
    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> delete(@PathVariable String nome) {
        if (!service.findById(nome).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(nome);
        return ResponseEntity.noContent().build();
    }
}
