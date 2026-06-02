package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Cane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per l'entità Cane.
 * Fornisce le operazioni CRUD sulla tabella CANE.
 * La chiave primaria è il numero di microchip (String).
 */
@Repository
public interface CaneRepository extends JpaRepository<Cane, String> {

    /**
     * Recupera tutti i cani associati a un determinato cliente.
     * Spring Data JPA genera automaticamente la query da questo nome.
     *
     * @param usernameCliente lo username del cliente
     * @return lista dei cani del cliente
     */
    List<Cane> findByUsernameCliente(String usernameCliente);
}
