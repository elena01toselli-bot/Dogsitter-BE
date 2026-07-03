package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.DTO.OffreDTO;
import com.progettodogsitting.dogsitting_backend.model.Servizio;
import com.progettodogsitting.dogsitting_backend.repository.ServizioRepository;
import com.progettodogsitting.dogsitting_backend.service.ServizioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/servizi")
@CrossOrigin(origins = "*")
public class ServizioController {


    @Autowired
    private ServizioService service;

    @Autowired
    private ServizioRepository servizioRepository;

    // Ritorna le categorie di servizi fisse
    @GetMapping("/categorie")
    public List<String> getCategorie() {

        // Restituisce una lista di stringhe dei servizi distinti presenti nel database
        return servizioRepository.findAll().stream().map(Servizio::getCategoria).distinct().toList();
    }

    // Ritorna i servizi offerti da uno specifico dogsitter
    @GetMapping("/dogsitter/{usernameDogsitter}")
    public List<OffreDTO> getByDogsitter(@PathVariable String usernameDogsitter) {

        return service.findByDogsitter(usernameDogsitter);
    }

    // Crea un nuovo servizio offerto
    @PostMapping
    public OffreDTO create(@RequestBody OffreDTO servizio) {
        // Salva i dati del nuovo servizio offerto e li restituisce
        return service.save(servizio);
    }

    // Aggiorna un servizio offerto esistente usando l'ID
    @PutMapping("/id")
    public ResponseEntity<OffreDTO> update(@RequestBody OffreDTO servizio) {

        // Verifica l'esistenza prima di fare l'update
        Optional<OffreDTO> existing = service.findById(servizio.getIdServizio(), servizio.getUsernameDogsitter());
        
        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
            
        }
        // Esegue il salvataggio
        return ResponseEntity.ok(service.save(servizio));
    }

    // Cancella un servizio offerto dato il suo ID
    // e l'username del dogsitter 
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestParam String username) {
        // Controlla se esiste per ritornare l'esito corretto
        if (!service.findById(id, username).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Cancella
        service.deleteById(id,username);

        return ResponseEntity.noContent().build();
    }
}
