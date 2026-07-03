package com.progettodogsitting.dogsitting_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progettodogsitting.dogsitting_backend.model.EsecuzioneServizio;


@Repository
public interface EsecuzioneServizioRepository extends JpaRepository<EsecuzioneServizio, Integer> {

    

}