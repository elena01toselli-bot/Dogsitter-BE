package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.DTO.LoginRequest;
import com.progettodogsitting.dogsitting_backend.DTO.RegistrazioneDTO;
import com.progettodogsitting.dogsitting_backend.config.JwtFilter;
import com.progettodogsitting.dogsitting_backend.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

// Controller REST per la gestione generale degli Utenti e l'autenticazione
@RestController
@RequestMapping("/api/utenti")
@CrossOrigin(origins = "*")
public class UtenteController {

    
    @Autowired
    private UtenteService service;

    // Filtro JWT per generare token di sessione sicuri
    @Autowired
    private JwtFilter jwtFilter;

    // Endpoint per l'accesso e generazione token (login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest credentials) {

        String usernamef = credentials.getUsername();
        String password = credentials.getPassword();
        String ruolo = credentials.getRuolo();

        // Valida le credenziali tramite il servizio (restituisce il ruolo effettivo)
        String validRuolo = service.login(usernamef, password, ruolo);

        // Se le credenziali sono corrette
        if (validRuolo != null) {
            // Genera il token JWT associato a questo username
            String token = jwtFilter.generaToken(usernamef);
            
            // Restituisce il token e i dati essenziali dell'utente
            return ResponseEntity.ok(Map.of(
                "token", token,
                "username",  usernamef,
                "ruolo", validRuolo
            ));
        }
        // Se le credenziali sono errate, restituisce errore 401
        return ResponseEntity.status(401).body(Map.of("error", "Credenziali errate"));
    }

    // Endpoint per la registrazione di un nuovo utente generico
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RegistrazioneDTO dto) {
     
        System.out.println("Ricevuta richiesta di registrazione: " + dto);
        
        // Salva l'utente tramite il servizio e ottiene lo username generato
        String username = service.save(dto);
     
        if (username != null) {

            String token = jwtFilter.generaToken(username);

            System.out.println("Utente creato con successo: " + username);

            return ResponseEntity.ok(Map.of(
                "token", token,
                "username",  username,
                "ruolo", dto.getRuolo().toLowerCase()
            ));

        }
     
        return ResponseEntity.status(400).body(Map.of("error", "Ruolo non riconosciuto"));
    }
}