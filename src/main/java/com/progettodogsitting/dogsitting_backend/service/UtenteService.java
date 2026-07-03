package com.progettodogsitting.dogsitting_backend.service;

import com.progettodogsitting.dogsitting_backend.DTO.RegistrazioneDTO;
import com.progettodogsitting.dogsitting_backend.DTO.UtenteDTO;
import com.progettodogsitting.dogsitting_backend.model.Cliente;
import com.progettodogsitting.dogsitting_backend.model.Dogsitter;
import com.progettodogsitting.dogsitting_backend.model.Utente;
import com.progettodogsitting.dogsitting_backend.repository.ClienteRepository;
import com.progettodogsitting.dogsitting_backend.repository.DogsitterRepository;
import com.progettodogsitting.dogsitting_backend.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UtenteService {


    @Autowired
    private UtenteRepository repository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DogsitterRepository dogsitterRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Ritorna la lista completa
    public List<Utente> findAll() {
        return repository.findAll();
    }

    public String save(RegistrazioneDTO dto) {
    
        String passwordHashata = passwordEncoder.encode(dto.getPassword());

        if (dto.getRuolo().equalsIgnoreCase("cliente")) {
        
            Cliente cliente = new Cliente();
            // copia campi comuni
            cliente.setUsername(dto.getUsername());
            cliente.setPassword(passwordHashata);
            cliente.setNomeBattesimo(dto.getNomeBattesimo());
            cliente.setCognome(dto.getCognome());
            cliente.setCap(dto.getCap());
            cliente.setNCivico(dto.getNCivico());
            cliente.setProvincia(dto.getProvincia());
            cliente.setVia(dto.getVia());
            cliente.setNTel(dto.getNTel());

            return clienteRepository.save(cliente).getUsername();

        } else if (dto.getRuolo().equalsIgnoreCase("dogsitter")) {
            
            Dogsitter dogsitter = new Dogsitter();
            // copia campi comuni
            dogsitter.setUsername(dto.getUsername());
            dogsitter.setPassword(passwordHashata);
            dogsitter.setNomeBattesimo(dto.getNomeBattesimo());
            dogsitter.setCognome(dto.getCognome());
            dogsitter.setCap(dto.getCap());
            dogsitter.setNCivico(dto.getNCivico());
            dogsitter.setProvincia(dto.getProvincia());
            dogsitter.setVia(dto.getVia());
            dogsitter.setNTel(dto.getNTel());
            // copia campi specifici dogsitter
            dogsitter.setMaxCani(dto.getMaxCani());
            dogsitter.setGiorniDisponibili(dto.getGiorniDisponibili());
            dogsitter.setTaglieCani(dto.getTaglieCani());


            return dogsitterRepository.save(dogsitter).getUsername();
        }

            return null; // ruolo non riconosciuto
    }
    
    
    // Cancella l'utente specificato
    public void deleteById(String username) {
        repository.deleteById(username);
    }

    // Verifica le credenziali per autorizzare l'accesso (restituisce il ruolo effettivo)
    public String login(String username, String password, String ruolo) {
     
        // Se il ruolo non è specificato, cerchiamo l'utente in tutte le tabelle
        if (ruolo == null || ruolo.trim().isEmpty()) {
            Optional<Cliente> cliente = clienteRepository.findById(username);
            if (cliente.isPresent() && passwordEncoder.matches(password, cliente.get().getPassword())) {
                return "cliente";
            }
            Optional<Dogsitter> dogsitter = dogsitterRepository.findById(username);
            if (dogsitter.isPresent() && passwordEncoder.matches(password, dogsitter.get().getPassword())) {
                return "dogsitter";
            }
            if (username.equals("admin")) {
                Optional<Utente> admin = repository.findById(username);
                if (admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())) {
                    return "amministratore";
                }
            }
            return null;
        }

        // Se il ruolo è specificato, controlliamo solo quello
        if (ruolo.equalsIgnoreCase("cliente")) {
            Optional<Cliente> cliente = clienteRepository.findById(username);
            if (cliente.isPresent() && passwordEncoder.matches(password, cliente.get().getPassword())) {
                return "cliente";
            }
        } else if (ruolo.equalsIgnoreCase("dogsitter")) {
            Optional<Dogsitter> dogsitter = dogsitterRepository.findById(username);
            if (dogsitter.isPresent() && passwordEncoder.matches(password, dogsitter.get().getPassword())) {
                return "dogsitter";
            }
        } else if (ruolo.equalsIgnoreCase("amministratore") && username.equals("admin")) {
            Optional<Utente> admin = repository.findById(username);
            if (admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())) {
                return "amministratore";
            }
        }

        return null; // credenziali errate o ruolo non riconosciuto
        
    }

    // Cerca utente come stringa semplice, a volte usato per validazioni
    public Optional<UtenteDTO> findByUsername(String username) {
        
        Optional<Utente> utente = repository.findByUsername(username);

        // Se l'utente esiste, mappa i dati essenziali in un DTO senza la password
        if (utente.isPresent()) {
            Utente u = utente.get();
            UtenteDTO dto = new UtenteDTO();
            dto.setUsername(u.getUsername());
            dto.setNomeBattesimo(u.getNomeBattesimo());
            dto.setCognome(u.getCognome());
            dto.setCap(u.getCap());
            dto.setNCivico(u.getNCivico());
            dto.setProvincia(u.getProvincia());
            dto.setVia(u.getVia());
            dto.setNTel(u.getNTel());

            // Non includiamo la password per motivi di sicurezza
            return Optional.of(dto);
        }

        return Optional.empty(); // Utente non trovato
    }

}
