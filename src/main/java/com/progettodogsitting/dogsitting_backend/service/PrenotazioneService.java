package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.PrenotazioneDTO;
import com.progettodogsitting.dogsitting_backend.model.EsecuzioneServizio;
import com.progettodogsitting.dogsitting_backend.model.LezioneId;
import com.progettodogsitting.dogsitting_backend.model.Prenotazione;
import com.progettodogsitting.dogsitting_backend.repository.CaneRepository;
import com.progettodogsitting.dogsitting_backend.repository.DogsitterRepository;
import com.progettodogsitting.dogsitting_backend.repository.EsecuzioneServizioRepository;
import com.progettodogsitting.dogsitting_backend.repository.LezioneRepository;
import com.progettodogsitting.dogsitting_backend.repository.PrenotazioneRepository;
import com.progettodogsitting.dogsitting_backend.repository.ServizioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository repository;    
    @Autowired
    private EsecuzioneServizioRepository esecuzioneServizioRepository;

    @Autowired
    private CaneRepository caneRepository;

    @Autowired
    private ServizioRepository servizioRepository;

    @Autowired
    private DogsitterRepository dogsitterRepository;

    @Autowired
    private LezioneRepository lezioneRepository;




    private PrenotazioneDTO convertToDTO(Prenotazione prenotazione) {
     
        PrenotazioneDTO dto = new PrenotazioneDTO();
     
        dto.setCodiceId(prenotazione.getCodiceId());
        dto.setPrezzoPattuito(prenotazione.getPrezzoPattuito());
        dto.setNMicrochip(prenotazione.getCane().getNMicrochip());
        dto.setNomeCane(prenotazione.getCane().getNome());
        dto.setCategoriaPrenotazione(prenotazione.getCane().getCliente().getUsername());
        dto.setUsernameCliente(prenotazione.getCane().getCliente().getUsername());

        if (prenotazione.getTipologiaAttivita().equalsIgnoreCase("lezione")) {

            dto.setDataSvolgimento(prenotazione.getLezione().getId().getData());
            dto.setOraSvolgimento(prenotazione.getLezione().getId().getOra());
            dto.setNomeCampo(prenotazione.getLezione().getCampoAddestramento().getNome());  
            dto.setCategoriaPrenotazione(prenotazione.getTipologiaAttivita());      
            dto.setCategoriaLezione(prenotazione.getLezione().getTipologiaLezione().getTipologia());

        }
        else if (prenotazione.getTipologiaAttivita().equalsIgnoreCase("dogsitter")) {

            // Per il dogsitting, i dettagli specifici sono nella tabella EsecuzioneServizio
            EsecuzioneServizio esecuzione = esecuzioneServizioRepository.findById(prenotazione.getCodiceId())
                .orElseThrow(() -> new RuntimeException("EsecuzioneServizio non trovata per prenotazione ID: " + prenotazione.getCodiceId()));

            dto.setDataSvolgimento(esecuzione.getDataSvolgimento());
            dto.setOraSvolgimento(esecuzione.getOraSvolgimento());
            dto.setCategoriaPrenotazione(prenotazione.getTipologiaAttivita());
            dto.setCategoriaServizio(esecuzione.getServizio().getCategoria());
            dto.setIdServizio(esecuzione.getServizio().getId());
            dto.setUsernameDogsitter(esecuzione.getDogsitter().getUsername());        
        }
        else {
            // Se la tipologia è sconosciuta, lancio un'eccezione 
            throw new IllegalArgumentException("Tipologia di attività sconosciuta");
        }

        return dto;
    }


    // Ritorna tutte le prenotazioni in cui è coinvolto un dato cliente
    public List<PrenotazioneDTO> findByCliente(String usernameCliente) {

        List<Prenotazione> prenotazioniClienteLezioni = repository.findByCane_Cliente_Username(usernameCliente);


        List<PrenotazioneDTO> prenotazioni =new ArrayList<>();

        for (Prenotazione pren : prenotazioniClienteLezioni) {
            
            try {
                prenotazioni.add(convertToDTO(pren));   

            } catch (Exception e) {
                // Log dell'errore e continua con la prossima prenotazione
                System.err.println("Errore nella conversione della prenotazione con ID " + pren.getCodiceId() + ": " + e.getMessage());

            }
        
        }
        
        return prenotazioni;
    }

    // Ritorna tutte le prenotazioni relative a un dato dogsitter
    public List<PrenotazioneDTO> findByDogsitter(String usernameDogsitter) {

        List<Prenotazione> prenotazioniDogsitter = repository.findByDogsitterUsername(usernameDogsitter);
        List<PrenotazioneDTO> prenotazioni =new ArrayList<>();

        for (Prenotazione pren : prenotazioniDogsitter) {
            
                prenotazioni.add(convertToDTO(pren));
        }
        return prenotazioni;
    }

    // Trova la prenotazione per ID univoco (codice)
    public Optional<PrenotazioneDTO> findByCodiceId(Integer id) {

        return Optional.of(convertToDTO(repository.findByCodiceId(id).orElseThrow(() -> new RuntimeException("Prenotazione non trovata per ID: " + id))));
    }

    // Salva i dati della prenotazione
    public PrenotazioneDTO save(PrenotazioneDTO prenotazione, boolean modifica) {
 
        Prenotazione prenotazioneEntity;

        if (modifica) {
            // MODIFICA: Recupera l'entità dal database
            prenotazioneEntity = repository.findById(prenotazione.getCodiceId())
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata per ID: " + prenotazione.getCodiceId()));
        } else {
            // CREAZIONE: Inizializza una nuova entità
            prenotazioneEntity = new Prenotazione();
        }

        // Aggiorna i campi comuni
        prenotazioneEntity.setPrezzoPattuito(prenotazione.getPrezzoPattuito());
        prenotazioneEntity.setCane(caneRepository.findById(prenotazione.getNMicrochip())
            .orElseThrow(() -> new RuntimeException("Cane non trovato per microchip: " + prenotazione.getNMicrochip())));

        
        // CASO DOGSITTER
        if(prenotazione.getCategoriaPrenotazione().equalsIgnoreCase("dogsitter")) {
            
            prenotazioneEntity.setTipologiaAttivita("dogsitter");
            Prenotazione savedPrenotazione = repository.save(prenotazioneEntity);

            if (savedPrenotazione == null) {
                throw new RuntimeException("Errore durante il salvataggio della prenotazione di dogsitting");
            }

            EsecuzioneServizio esecuzione;

            if (modifica) {

                // MODIFICA
                esecuzione = esecuzioneServizioRepository.findById(savedPrenotazione.getCodiceId())
                    .orElseThrow(() -> new RuntimeException("EsecuzioneServizio non trovata per prenotazione ID: " + savedPrenotazione.getCodiceId()));

            } else {
                // CREAZIONE
                esecuzione = new EsecuzioneServizio();
                esecuzione.setPrenotazione(savedPrenotazione); // Impostato solo alla creazione
            }

            // Aggiorna i campi dell'esecuzione
            esecuzione.setDataSvolgimento(prenotazione.getDataSvolgimento());
            esecuzione.setOraSvolgimento(prenotazione.getOraSvolgimento());
            esecuzione.setServizio(servizioRepository.findById(prenotazione.getIdServizio())
                .orElseThrow(() -> new RuntimeException("Servizio non trovato per ID: " + prenotazione.getIdServizio())));
            esecuzione.setDogsitter(dogsitterRepository.findById(prenotazione.getUsernameDogsitter())
                .orElseThrow(() -> new RuntimeException("Dogsitter non trovato per username: " + prenotazione.getUsernameDogsitter())));

            EsecuzioneServizio esecuzionesaved = esecuzioneServizioRepository.save(esecuzione);
            
            if (esecuzionesaved == null) {
                throw new RuntimeException("Errore durante il salvataggio dell'esecuzione del servizio");
            }

            return convertToDTO(savedPrenotazione);
        }
    
        
        // CASO LEZIONE
        if(prenotazione.getCategoriaPrenotazione().equalsIgnoreCase("lezione")) {

            prenotazioneEntity.setTipologiaAttivita("lezione");
            prenotazioneEntity.setLezione(lezioneRepository.findById(new LezioneId(prenotazione.getNomeCampo(), prenotazione.getOraSvolgimento(), prenotazione.getDataSvolgimento()))
                .orElseThrow(() -> new RuntimeException("Lezione non trovata per campo: " + prenotazione.getNomeCampo() + ", data: " + prenotazione.getDataSvolgimento() + ", ora: " + prenotazione.getOraSvolgimento())));
    
            Prenotazione savedPrenotazione = repository.save(prenotazioneEntity);

            if (savedPrenotazione == null) {
                throw new RuntimeException("Errore durante il salvataggio della prenotazione di lezione");
            }

            return convertToDTO(savedPrenotazione);    
        }
        
        throw new IllegalArgumentException("Categoria di prenotazione sconosciuta: " + prenotazione.getCategoriaPrenotazione());
    }
    
    // Elimina la prenotazione associata a questo ID
    public void deleteByCodiceId(Integer id) {

        repository.deleteById(id);
    }

}