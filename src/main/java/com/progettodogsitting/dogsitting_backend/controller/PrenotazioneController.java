package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.DTO.PrenotazioneDTO;
import com.progettodogsitting.dogsitting_backend.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/prenotazioni")
@CrossOrigin(origins = "*")
public class PrenotazioneController {   


    @Autowired
    private PrenotazioneService service;

    // Ritorna l'elenco delle prenotazioni relative a un determinato cliente
    @GetMapping("/cliente/{usernameCliente}")
    public List<PrenotazioneDTO> getByCliente(@PathVariable String usernameCliente) {

        // devi prendere tutte le prenotazioni sia dog sitter sia lezioni 
        return service.findByCliente(usernameCliente);
    }

    // Ritorna l'elenco delle prenotazioni per un determinato dogsitter
    @GetMapping("/dogsitter/{usernameDogsitter}")
    public List<PrenotazioneDTO> getByDogsitter(@PathVariable String usernameDogsitter) {

        return service.findByDogsitter(usernameDogsitter);
    }

    // Cerca una prenotazione in base al suo codice identificativo
    @GetMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> getById(@PathVariable Integer id) {
        try {
        
            return service.findByCodiceId(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } 
        catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
}

    // Crea una nuova prenotazione
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PrenotazioneDTO prenotazione) {

        boolean modifica = false; // Indica che si tratta di una nuova prenotazione, non di una modifica
        try
        {
            return ResponseEntity.ok(service.save(prenotazione,modifica));
        }
        catch (RuntimeException e) {

            // restituisco il messaggio di errore "e" al frontend 
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // Aggiorna i dati di una prenotazione esistente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody PrenotazioneDTO prenotazione) {
        
        // Verifica esistenza
        if (!service.findByCodiceId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Imposta l'ID per sicurezza prima del salvataggio
        prenotazione.setCodiceId(id);
        
        boolean modifica = true;

        try{
            service.save(prenotazione,modifica);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    // Rimuove una prenotazione specifica dal database
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        
        // Verifica esistenza
        if (!service.findByCodiceId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Cancella la prenotazione
        service.deleteByCodiceId(id);
        return ResponseEntity.noContent().build();
    }
}
