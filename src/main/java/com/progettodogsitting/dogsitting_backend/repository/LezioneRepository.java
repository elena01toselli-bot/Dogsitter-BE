package com.progettodogsitting.dogsitting_backend.repository;

import com.progettodogsitting.dogsitting_backend.model.Lezione;
import com.progettodogsitting.dogsitting_backend.model.LezioneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per l'entità Lezione.
 * La chiave primaria è composta: (nomeCampo, ora, data) — rappresentata da LezioneId.
 */
@Repository
public interface LezioneRepository extends JpaRepository<Lezione, LezioneId> {

    /**
     * Recupera tutte le lezioni associate a un determinato campo di addestramento.
     * Usato dalla lista-cdc per mostrare le lezioni disponibili.
     *
     * @param nomeCampo il nome del campo
     * @return lista delle lezioni del campo
     */
    List<Lezione> findByIdNomeCampo(String nomeCampo);
}
