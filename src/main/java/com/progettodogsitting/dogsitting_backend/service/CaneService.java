package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.CaneDTO;
import com.progettodogsitting.dogsitting_backend.model.Cane;
import com.progettodogsitting.dogsitting_backend.repository.CaneRepository;
import com.progettodogsitting.dogsitting_backend.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CaneService {


    @Autowired
    private CaneRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;


    private Cane creaCane(CaneDTO dto) {
     
        Cane cane = new Cane();
     
        cane.setNMicrochip(dto.getNMicrochip());
        cane.setNome(dto.getNome());
        cane.setRazza(dto.getRazza());
        cane.setTaglia(dto.getTaglia());
        cane.setDataNascita(dto.getDataNascita());
        cane.setNoteComportamento(dto.getNoteComportamento());
        cane.setCliente(clienteRepository.findById(dto.getUsernameCliente()).orElse(null));
        
        return cane;
    }

    private CaneDTO creaCaneDTO(Cane cane) {
     
        CaneDTO dto = new CaneDTO();
        dto.setNMicrochip(cane.getNMicrochip());
        dto.setNome(cane.getNome());
        dto.setRazza(cane.getRazza());
        dto.setTaglia(cane.getTaglia());
        dto.setDataNascita(cane.getDataNascita());
        dto.setNoteComportamento(cane.getNoteComportamento());
        dto.setUsernameCliente(cane.getCliente().getUsername());
        return dto;
    }


    // Ritorna i numeri di microchip dei cani appartenenti a un cliente specifico
    public List<String> findMicrochipByCliente(String usernameCliente) {
        
        return repository.findNMicrochipByCliente_Username(usernameCliente);
    }

    // Ritorna i cani appartenenti a un cliente specifico
    public List<CaneDTO> findByCliente(String usernameCliente) {

        List<Cane> cani = repository.findByCliente_Username(usernameCliente);
        
        List<CaneDTO> caniDTO = new ArrayList<>();
        
        for (Cane cane : cani) {
            caniDTO.add(creaCaneDTO(cane));
        }
        
        return caniDTO;
    }

    // Cerca un cane usando il suo numero di microchip
    public Optional<CaneDTO> findById(String nMicrochip) {

        Optional<Cane> cane = repository.findById(nMicrochip);
        
        if(cane.isPresent()) {
            return Optional.of(creaCaneDTO(cane.get()));
        } else {
            return Optional.empty();
        }
        
    }

    // Inserisce o aggiorna le informazioni del cane
    public boolean save(CaneDTO cane) {
        try {
            repository.save(creaCane(cane));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Rimuove i dati di un cane in base al microchip
    public void deleteById(String nMicrochip) {
        repository.deleteById(nMicrochip);
    }
}
