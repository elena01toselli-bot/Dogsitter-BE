package com.progettodogsitting.dogsitting_backend.controller;

import com.progettodogsitting.dogsitting_backend.service.DatiGeneraliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controller utile a fornire dati statici general purpose all'applicazione frontend
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DatiGeneraliController {

    
    @Autowired
    private DatiGeneraliService service;

    // Endpoint pubblico per caricare la lista di tutte le sigle delle province italiane
    @GetMapping("/province")
    public List<String> getProvince() {


        return service.getProvince();
    }
}
