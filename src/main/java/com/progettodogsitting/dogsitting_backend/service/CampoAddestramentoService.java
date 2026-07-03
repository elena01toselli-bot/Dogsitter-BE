package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.model.CampoAddestramento;
import com.progettodogsitting.dogsitting_backend.repository.CampoAddestramentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampoAddestramentoService {

    @Autowired
    private CampoAddestramentoRepository repository;

    // Metodo per trovare tutti i campi di addestramento
    public List<CampoAddestramento> findAll() 
    {
        return repository.findAll();
    }

    // Metodo per trovare un campo specifico in base al nome
    public Optional<CampoAddestramento> findById(String nome) {
        return repository.findById(nome);
    }

    // Metodo per salvare un nuovo campo o aggiornarne uno esistente
    public boolean save(CampoAddestramento campo) {

        try {
            repository.save(campo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Metodo per cancellare un campo tramite il nome
    public void deleteById(String nome) {
        repository.deleteById(nome);
    }
}
