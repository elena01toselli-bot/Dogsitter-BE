package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.DogsitterDTO;
import com.progettodogsitting.dogsitting_backend.model.Dogsitter;
import com.progettodogsitting.dogsitting_backend.repository.DogsitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DogsitterService {

    @Autowired
    private DogsitterRepository repository;

    private DogsitterDTO transformToDTO(Dogsitter dogsitter) {
     
        DogsitterDTO dto = new DogsitterDTO();

        dto.setUsername(dogsitter.getUsername());
        dto.setNomeBattesimo(dogsitter.getNomeBattesimo());
        dto.setCognome(dogsitter.getCognome());
        dto.setCap(dogsitter.getCap());
        dto.setNCivico(dogsitter.getNCivico());
        dto.setProvincia(dogsitter.getProvincia());
        dto.setVia(dogsitter.getVia());
        dto.setNTel(dogsitter.getNTel());

        // quelli in piu rispetto al cliente
        
        dto.setMaxCani(dogsitter.getMaxCani());
    
        // new ArrayList<>(...) forza Hibernate a iterare ORA, mentre la sessione è ancora aperta
        dto.setTaglieCani(new ArrayList<>(dogsitter.getTaglieCani()));
        dto.setGiorniDisponibili(new ArrayList<>(dogsitter.getGiorniDisponibili()));
        
        dto.setRuolo("dogsitter");
        
        return dto;
    }

    // Trova tutti i dogsitter
    public List<DogsitterDTO> findAll() {

        List<Dogsitter> lista = repository.findAll();

        List<DogsitterDTO> dtoList = new ArrayList<>();

        for (Dogsitter dogsitter : lista) {

            dtoList.add(transformToDTO(dogsitter));
        }

        return dtoList;
    }

    // Trova un singolo dogsitter in base allo username
    public Optional<DogsitterDTO> findById(String username) {
        Optional<Dogsitter> dogsitter = repository.findById(username);

        if (dogsitter.isPresent()) {
                        
            return Optional.of(transformToDTO(dogsitter.get()));

        } else {
            return Optional.empty();
        }
}

    // Aggiorna un dogsitter
    public DogsitterDTO save(DogsitterDTO dogsitter) {

        Dogsitter saved = repository.findById(dogsitter.getUsername()).orElseThrow(() -> new RuntimeException("Dogsitter non trovato"));

        // modifico i campi del dogsitter recuperato con quelli del DTO in ingresso
        
        saved.setNomeBattesimo(dogsitter.getNomeBattesimo());
        saved.setCognome(dogsitter.getCognome());
        saved.setCap(dogsitter.getCap());
        saved.setNCivico(dogsitter.getNCivico());
        saved.setProvincia(dogsitter.getProvincia());
        saved.setVia(dogsitter.getVia());
        saved.setNTel(dogsitter.getNTel());
        saved.setMaxCani(dogsitter.getMaxCani());
        saved.setGiorniDisponibili(dogsitter.getGiorniDisponibili());
        saved.setTaglieCani(dogsitter.getTaglieCani());

        return transformToDTO(repository.save(saved));
    
    }

    // Cancella il dogsitter tramite username
    public void deleteById(String username) {
        repository.deleteById(username);
    }
}
