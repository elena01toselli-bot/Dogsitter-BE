package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.DTO.UtenteDTO;
import com.progettodogsitting.dogsitting_backend.config.JwtFilter;   // ← era JwtUtils
import com.progettodogsitting.dogsitting_backend.model.Utente;
import com.progettodogsitting.dogsitting_backend.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/utenti")
@CrossOrigin(origins = "*")
public class UtenteController {

    @Autowired
    private UtenteService service;

    @Autowired
    private JwtFilter jwtFilter;   // ← era JwtUtils, ora inietta il filtro direttamente

    // POST /api/utenti/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        UtenteDTO utente = service.login(body.get("username"), body.get("password"));

        if (utente != null) {
            String token = jwtFilter.generaToken(utente.getUsername());  // ← usa il filtro
            return ResponseEntity.ok(Map.of(
                "token", token,
                "user",  utente
            ));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenziali errate"));
    }

    // GET /api/utenti/{username}   ← rimosso il "/api/utenti/" ridondante
    @GetMapping("/{username}")
    public ResponseEntity<Utente> getById(@PathVariable String username) {
        Optional<Utente> utente = service.findById(username);
        return utente.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/utenti/create   ← rimosso il "/api/utenti/" ridondante
    @PostMapping("/create")
    public Utente create(@RequestBody Utente utente) {
        return service.save(utente);
    }
}