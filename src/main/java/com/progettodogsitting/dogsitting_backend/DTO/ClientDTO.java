package com.progettodogsitting.dogsitting_backend.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // Per includere i campi di UtenteDTO nei metodi equals e hashCode
@Data
public class ClientDTO  extends UtenteDTO
{   
}
