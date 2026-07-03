package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.ClientDTO;
import com.progettodogsitting.dogsitting_backend.model.Cliente;
import com.progettodogsitting.dogsitting_backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    private ClientDTO transformToDTO(Cliente cliente) {
     
        ClientDTO dto = new ClientDTO();

        dto.setUsername(cliente.getUsername());
        dto.setNomeBattesimo(cliente.getNomeBattesimo());
        dto.setCognome(cliente.getCognome());
        dto.setCap(cliente.getCap());
        dto.setNCivico(cliente.getNCivico());
        dto.setProvincia(cliente.getProvincia());
        dto.setVia(cliente.getVia());
        dto.setNTel(cliente.getNTel());
        dto.setRuolo("cliente");
        return dto;
    }

    // Recupera la lista totale dei clienti
    public List<ClientDTO> findAll() {

        List<Cliente> lista = repository.findAll();
        List<ClientDTO> dtoList = new ArrayList<>();

        for (Cliente cliente : lista) {

            dtoList.add(transformToDTO(cliente));
        }

        return dtoList;

    }

    // Recupera un cliente specifico usando l'username come PK
    public Optional<ClientDTO> findById(String username) {
        
       Optional<Cliente> cliente = repository.findById(username);

       if(cliente.isPresent()) {
                        
            return Optional.of(transformToDTO(cliente.get()));


       } else {
           return Optional.empty();
       }
    }

    // Aggiorna le informazioni del cliente nel DB
    public ClientDTO save(ClientDTO cliente) {
        
        // Recuperi l'entità esistente dal DB
        Optional<Cliente> clienteEsistente = repository.findById(cliente.getUsername());


        Cliente c = clienteEsistente.get();

        // Aggiorni solo i campi del DTO, la password non la tocchi
        c.setNomeBattesimo(cliente.getNomeBattesimo());
        c.setCognome(cliente.getCognome());
        c.setCap(cliente.getCap()); 
        c.setNCivico(cliente.getNCivico());
        c.setProvincia(cliente.getProvincia());
        c.setVia(cliente.getVia());
        c.setNTel(cliente.getNTel());

        // la password rimane quella vecchia
        return transformToDTO(repository.save(c));
      
    }

    // Cancella il profilo di un cliente dal database
    public void deleteById(String username) {
        repository.deleteById(username);
    }
}
