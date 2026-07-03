package com.progettodogsitting.dogsitting_backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ESECUZIONE_SERVIZIO")
public class EsecuzioneServizio {

    // Chiave primaria coincide col codice prenotazione
    @Id
    @Column(name = "CODICE_ID_PREN", nullable = false)
    private Integer codiceIdPren;

    // Orario in cui è stato completato il servizio
    @Column(name = "ORA_SVOLGIMENTO", nullable = false)
    private LocalTime oraSvolgimento;

    // Data in cui è stato completato il servizio
    @Column(name = "DATA_SVOLGIMENTO", nullable = false)
    private LocalDate dataSvolgimento;

    // Relazione uno a uno con la prenotazione di origine
    @OneToOne
    @MapsId
    @JoinColumn(name = "CODICE_ID_PREN", insertable = false, updatable = false)
    private Prenotazione prenotazione;

    // Il servizio specifico eseguito (es. toelettatura)
    @ManyToOne
    @JoinColumn(name = "ID_SERVIZIO", nullable = false)
    private Servizio servizio;

    // Il dogsitter che ha effettuato la prestazione
    @ManyToOne
    @JoinColumn(name = "USERNAME_D", nullable = false)
    private Dogsitter dogsitter;
}
