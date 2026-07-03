package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.OffreDTO;
import com.progettodogsitting.dogsitting_backend.model.Offre;
import com.progettodogsitting.dogsitting_backend.model.OffreId;
import com.progettodogsitting.dogsitting_backend.model.Servizio;
import com.progettodogsitting.dogsitting_backend.repository.DogsitterRepository;
import com.progettodogsitting.dogsitting_backend.repository.OffreRepository;
import com.progettodogsitting.dogsitting_backend.repository.ServizioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServizioService {

    @Autowired
    private OffreRepository repository;

    @Autowired
    private DogsitterRepository dogsitterRepository;

    @Autowired
    private ServizioRepository servizioRepository;

    private OffreDTO convertToDTO(Offre offre) {
        OffreDTO dto = new OffreDTO();
        dto.setIdServizio(offre.getId().getIdServizio());
        dto.setUsernameDogsitter(offre.getId().getUsernameDogsitter());
        dto.setCategoria(offre.getServizio().getCategoria());
        dto.setDurata(offre.getServizio().getDurata());
        dto.setPrezzoListino(offre.getPrezzoListino());
        return dto;
    }

    // Ritorna la lista dei servizi offerti da un determinato dogsitter
    public List<OffreDTO> findByDogsitter(String usernameDogsitter) {
        List<Offre> offerte = repository.findByDogsitter_Username(usernameDogsitter);
        return offerte.stream().map(this::convertToDTO).toList();
    }

    // Cerca un servizio per ID primario
    public Optional<OffreDTO> findById(Integer id, String username) {
        return repository.findById(new OffreId(username, id)).map(this::convertToDTO);
    }

    // Salva o aggiorna un ServizioOfferto nel database
    public OffreDTO save(OffreDTO dto) {

        Servizio servizio;

        if (dto.getIdServizio() != null) {
            // MODIFICA: usa il servizio esistente e aggiorna i campi
            servizio = servizioRepository.findById(dto.getIdServizio())
                .orElseThrow(() -> new RuntimeException("Servizio non trovato con id: " + dto.getIdServizio()));
            servizio.setCategoria(dto.getCategoria());
            servizio.setDurata(dto.getDurata());
            servizio = servizioRepository.save(servizio);
        } else {
            
            // CREAZIONE
            servizio = new Servizio();
            servizio.setCategoria(dto.getCategoria());
            servizio.setDurata(dto.getDurata());
            servizio = servizioRepository.save(servizio);
        }

        // Generiamo l'ID composto dell'offerta
        OffreId offreId = new OffreId(dto.getUsernameDogsitter(), servizio.getId());

        // Cerchiamo se l'offerta esiste già nel DB. 
        // Se esiste, Hibernate caricherà l'oggetto "connesso" al DB; se non esiste, ne creiamo uno nuovo.
        Offre offre = repository.findById(offreId).orElse(new Offre());

        // Se è un record NUOVO (ovvero l'id dentro l'oggetto è ancora nullo), settiamo le relazioni e le chiavi
        if (offre.getId() == null) {
            offre.setId(offreId);
            offre.setDogsitter(dogsitterRepository.findById(dto.getUsernameDogsitter())
                .orElseThrow(() -> new RuntimeException("Dogsitter non trovato: " + dto.getUsernameDogsitter())));
            offre.setServizio(servizio);
        }

        // Aggiorniamo il prezzo di listino (funziona sia per inserimento che per modifica)
        offre.setPrezzoListino(dto.getPrezzoListino());

        // Ora Spring capirà autonomamente se fare INSERT (se era nuovo) o UPDATE (se esisteva già)
        Offre saved = repository.save(offre);
        return convertToDTO(saved);
    }

    // Elimina il record
    public void deleteById(Integer id, String username) {
        repository.deleteById(new OffreId(username, id));
    }
}