package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.service.DatiGeneraliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per i dati generali e di configurazione dell'app.
 *
 * Espone gli endpoint:
 *   GET /api/province → lista delle province italiane
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DatiGeneraliController {

    @Autowired
    private DatiGeneraliService service;

    /**
     * Restituisce la lista delle province italiane.
     * Usata dal frontend per popolare la select "provincia"
     * nei form di registrazione e modifica profilo.
     *
     * GET /api/province
     *
     * @return lista di sigle delle province italiane
     */
    @GetMapping("/province")
    public List<String> getProvince() {
        return service.getProvince();
    }
}
