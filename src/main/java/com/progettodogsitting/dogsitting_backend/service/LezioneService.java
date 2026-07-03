package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.LezioneDTO;
import com.progettodogsitting.dogsitting_backend.model.CampoAddestramento;
import com.progettodogsitting.dogsitting_backend.model.Lezione;
import com.progettodogsitting.dogsitting_backend.model.LezioneId;
import com.progettodogsitting.dogsitting_backend.model.TipologiaLezione;
import com.progettodogsitting.dogsitting_backend.repository.CampoAddestramentoRepository;
import com.progettodogsitting.dogsitting_backend.repository.LezioneRepository;
import com.progettodogsitting.dogsitting_backend.repository.TipologiaLezioneRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class LezioneService {

    // Iniettiamo il repository per accedere al database
    @Autowired
    private LezioneRepository repository;

    @Autowired
    private CampoAddestramentoRepository campoRepository;
    
    @Autowired
    private TipologiaLezioneRepository tipologiaRepository;

    private Lezione creaLezione(LezioneDTO dto) {

        // Recupero le entità collegate tramite i loro repository
        CampoAddestramento campo = campoRepository.findById(dto.getNomeCampo())
            .orElseThrow(() -> new EntityNotFoundException("Campo non trovato"));

        TipologiaLezione tipologia = tipologiaRepository.findById(dto.getTipologia())
            .orElseThrow(() -> new EntityNotFoundException("Tipologia non trovata"));

        // Costruisci la PK composta
        LezioneId id = new LezioneId();
        id.setNomeCampo(dto.getNomeCampo());
        id.setOra(dto.getOra());
        id.setData(dto.getData());  

        // Costruisci l'entity
        Lezione lezione = new Lezione();
        lezione.setId(id);
        lezione.setCampoAddestramento(campo);
        lezione.setTipologiaLezione(tipologia);
        lezione.setMaxPartecipanti(dto.getMaxPartecipanti());

        return lezione;
    }

    private LezioneDTO convertToDTO(Lezione lezione) {
        
        LezioneDTO dto = new LezioneDTO();
        
        // costruisco chiave di lezione
        dto.setNomeCampo(lezione.getId().getNomeCampo());
        dto.setOra(lezione.getId().getOra());
        dto.setData(lezione.getId().getData());

        // recupero tipologia,costo,maxPartecipanti 
        dto.setTipologia(lezione.getTipologiaLezione().getTipologia());
        dto.setCosto(lezione.getTipologiaLezione().getCosto());
        dto.setMaxPartecipanti(lezione.getMaxPartecipanti());

        return dto;
    }

    public Map<String, BigDecimal> getAllTipologie() {
     
        List<TipologiaLezione> tipologie = tipologiaRepository.findAll();
     
        Map<String, BigDecimal> result = new HashMap<>();

        for (TipologiaLezione t : tipologie) {
            
            result.put(t.getTipologia(), t.getCosto());
        }

        return result;
    }


    // Recupera tutte le lezioni di un campo
    public List<LezioneDTO> findByCampo(String nomeCampo) 
    {
        // Recupera tutte le lezioni di quel campo da LEZIONE
        List<Lezione> lezioni = repository.findByIdNomeCampo(nomeCampo);

        List<LezioneDTO> lezioniDTO = new ArrayList<>();

        for (Lezione lezione : lezioni) {
            lezioniDTO.add(convertToDTO(lezione));
        }

        return lezioniDTO;
    }

    // Salva o aggiorna una lezione
    public LezioneDTO save(LezioneDTO lezione) 
    {
        Lezione lezioneSalvata = repository.save(creaLezione(lezione));

        if (lezioneSalvata == null) {
            throw new RuntimeException("Errore durante il salvataggio della lezione");
        }

        return convertToDTO(lezioneSalvata);

    }



    // Recupera una lezione specifica in base al suo ID composto
    public Optional<Lezione> findById(LezioneId id) {
        return repository.findById(id);
    }


    // Elimina una lezione in base al suo ID
    public void deleteById(LezioneId id) {
        repository.deleteById(id);
    }
}
