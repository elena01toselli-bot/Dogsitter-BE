package com.progettodogsitting.dogsitting_backend.DTO;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // Per includere i campi di UtenteDTO nei metodi equals e hashCode
@Data  
public class DogsitterDTO extends UtenteDTO 
{

    private Integer maxCani;
    private List<String> giorniDisponibili;
    private List<String> taglieCani;

}
