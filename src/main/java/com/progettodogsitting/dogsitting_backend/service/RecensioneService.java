package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.RecensioneDTO;
import com.progettodogsitting.dogsitting_backend.model.Recensione;
import com.progettodogsitting.dogsitting_backend.model.RecensioneId;
import com.progettodogsitting.dogsitting_backend.repository.ClienteRepository;
import com.progettodogsitting.dogsitting_backend.repository.DogsitterRepository;
import com.progettodogsitting.dogsitting_backend.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository repository;

    @Autowired
    private DogsitterRepository dogsitterService;
    @Autowired
    private ClienteRepository clienteService;

    private Recensione convertToEntity(RecensioneDTO dto) {
      
        Recensione recensione = new Recensione();
      
        recensione.setId(new RecensioneId(dto.getUsernameCliente(), dto.getUsernameDogsitter()));
      
        recensione.setVoto(dto.getVoto());

        recensione.setCliente(clienteService.findById(dto.getUsernameCliente()).orElse(null));
        recensione.setDogsitter(dogsitterService.findById(dto.getUsernameDogsitter()).orElse(null));
      
        recensione.setCommento(dto.getCommento());
      
        recensione.setData(dto.getData());
      
        return recensione;
    }

    private RecensioneDTO convertToDTO(Recensione recensione) {
     
        RecensioneDTO dto = new RecensioneDTO();
     
        dto.setUsernameCliente(recensione.getCliente().getUsername());
        dto.setUsernameDogsitter(recensione.getDogsitter().getUsername());
        dto.setVoto(recensione.getVoto());
        dto.setCommento(recensione.getCommento());
        dto.setData(recensione.getData());
     
        return dto;
    }

    // Ritorna le recensioni relative a un dato dogsitter
    public List<RecensioneDTO> findByDogsitter(String usernameDogsitter) {

        List<Recensione> recensioni = repository.findByIdUsernameDogsitter(usernameDogsitter);
        
        List<RecensioneDTO> recensioniDTO =new ArrayList<>();
        
        for (Recensione recensione : recensioni) {
            recensioniDTO.add(convertToDTO(recensione));
        }
        return recensioniDTO;
    }

    // Ritorna le recensioni scritte da un determinato cliente
    public List<RecensioneDTO> findByCliente(String usernameCliente) {
        List<Recensione> recensioni = repository.findByIdUsernameCliente(usernameCliente);

        List<RecensioneDTO> recensioniDTO = new ArrayList<>();

        for (Recensione recensione : recensioni) {
            recensioniDTO.add(convertToDTO(recensione));
        }
        
        return recensioniDTO;
    }

    // Inserisce o aggiorna una recensione
    public RecensioneDTO save(RecensioneDTO recensione) {

                
        return convertToDTO(repository.save(convertToEntity(recensione)));
    
    }

    // Elimina una specifica recensione identificata dal suo ID composto
    public void deleteById(RecensioneId id) {
        repository.deleteById(id);
    }

    // Elimina fisicamente tutte le recensioni collegate a un utente cliente
    @Transactional
    public void deleteAllByCliente(String usernameCliente) {
        repository.deleteByIdUsernameCliente(usernameCliente);
    }

    // Trova tutti gli ID di dogsitter che hanno ricevuto recensioni
    public List<String> findDogsitterDisponibili() {
        return repository.findDistinctIdUsernameDogsitterBy();
    }
}
