package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.model.Lezione;
import com.progettodogsitting.dogsitting_backend.model.LezioneId;
import com.progettodogsitting.dogsitting_backend.service.LezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per la gestione delle Lezioni di addestramento.
 *
 * Espone gli endpoint:
 *   GET    /api/lezioni/campo/{nomeCampo}         → lezioni di un campo
 *   POST   /api/lezioni                           → creazione lezione
 *   PUT    /api/lezioni                           → aggiornamento lezione (body con id composta)
 *   DELETE /api/lezioni?nomeCampo=&ora=&data=     → cancellazione lezione via query params
 */
@RestController
@RequestMapping("/api/lezioni")
@CrossOrigin(origins = "*")
public class LezioneController {

    @Autowired
    private LezioneService service;

    /**
     * Recupera tutte le lezioni di un determinato campo di addestramento.
     *
     * GET /api/lezioni/campo/{nomeCampo}
     *
     * @param nomeCampo il nome del campo
     * @return lista di lezioni del campo
     */
    @GetMapping("/campo/{nomeCampo}")
    public List<Lezione> getByCampo(@PathVariable String nomeCampo) {
        return service.findByCampo(nomeCampo);
    }

    /**
     * Crea una nuova lezione in un campo di addestramento.
     * La chiave primaria (nomeCampo, ora, data) deve essere settata nell'oggetto.
     *
     * POST /api/lezioni
     * Body: oggetto Lezione
     *
     * @param lezione i dati della nuova lezione
     * @return la lezione creata
     */
    @PostMapping
    public Lezione create(@RequestBody Lezione lezione) {
        return service.save(lezione);
    }

    /**
     * Aggiorna i dati di una lezione esistente.
     * La lezione viene identificata tramite la chiave composta nell'id dell'oggetto.
     *
     * PUT /api/lezioni
     * Body: oggetto Lezione con id composta
     *
     * @param lezione la lezione con i nuovi dati (deve contenere l'id composta)
     * @return 200 con la lezione aggiornata, oppure 404 se non esiste
     */
    @PutMapping
    public ResponseEntity<Lezione> update(@RequestBody Lezione lezione) {
        if (!service.findById(lezione.getId()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.save(lezione));
    }

    /**
     * Elimina una lezione tramite i tre parametri della chiave composta.
     * Il frontend passa nomeCampo, ora e data come query parameters.
     *
     * DELETE /api/lezioni?nomeCampo={nome}&ora={ora}&data={data}
     *
     * @param nomeCampo il nome del campo della lezione
     * @param ora       l'ora della lezione
     * @param data      la data della lezione
     * @return 204 No Content se eliminata, oppure 404 se non esiste
     */
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam String nomeCampo,
                                       @RequestParam String ora,
                                       @RequestParam String data) {
        LezioneId id = new LezioneId(nomeCampo, ora, data);
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
