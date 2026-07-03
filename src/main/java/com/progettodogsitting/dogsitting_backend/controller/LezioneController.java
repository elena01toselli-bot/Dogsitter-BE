package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.DTO.LezioneDTO;
import com.progettodogsitting.dogsitting_backend.model.Lezione;
import com.progettodogsitting.dogsitting_backend.model.LezioneId;
import com.progettodogsitting.dogsitting_backend.service.LezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/lezioni")
@CrossOrigin(origins = "*")
public class LezioneController {


    @Autowired
    private LezioneService service;

    // Endpoint per ottenere tutte le tipologie delle lezioni
    @GetMapping("/tipologie")
    public Map <String, BigDecimal> getAllTipologie() {
        return service.getAllTipologie();
    }

    // Endpoint per ottenere le lezioni associate a un campo specifico
    @GetMapping("/campo/{nomeCampo}")
    public List<LezioneDTO> getByCampo(@PathVariable String nomeCampo) {
        return service.findByCampo(nomeCampo);
    }

    // Endpoint per creare una nuova lezione
    @PostMapping
    public LezioneDTO create(@RequestBody LezioneDTO lezione) 
    {

        return service.save(lezione);
    }

    // Endpoint per aggiornare una lezione esistente
    @PutMapping
    public ResponseEntity<?> update(@RequestBody LezioneDTO lezione) {

        // Verifica preliminare sulla presenza dei campi necessari nell'oggetto JSON
        if (lezione.getNomeCampo() == null) {
            return ResponseEntity.badRequest().build();
        }
        // Controllo se la lezione da aggiornare esiste già per quel campo
        if (!service.findByCampo(lezione.getNomeCampo()).contains(lezione)) {
            return ResponseEntity.notFound().build();
        }
        // Effettua l'aggiornamento
        try {
                LezioneDTO salvata = service.save(lezione);
                
                return ResponseEntity.ok(salvata);

            } catch (RuntimeException e) {
                
                // 500 Internal Server Error in caso di eccezione durante il salvataggio
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }

    // Endpoint per cancellare una lezione 
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam String nomeCampo,
                                       @RequestParam LocalTime ora,
                                       @RequestParam LocalDate data) {

        // Ricostruiamo la PK composta per avviare la ricerca
        LezioneId id = new LezioneId();
        id.setNomeCampo(nomeCampo);
        id.setOra(ora);
        id.setData(data);

        // Cerchiamo la lezione nel database tramite il service
        Optional<Lezione> lezione = service.findById(id);
        
        // Se non viene trovata, ritorna codice 404 (Not Found)
        if (lezione.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Esegue la rimozione fisica se l'entità è presente
        service.deleteById(id);
        
        // Ritorna codice 204 (No Content) per confermare la cancellazione
        return ResponseEntity.noContent().build();
    }
}
