package com.progettodogsitting.dogsitting_backend.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Service per i dati generali e di configurazione dell'applicazione.
 * Al momento fornisce la lista delle province italiane,
 * usata dal frontend per popolare la tendina di selezione nella registrazione.
 *
 * Nota: in futuro potrebbe leggere da DB o da file di configurazione.
 */
@Service
public class DatiGeneraliService {

    /**
     * Restituisce la lista delle province italiane come stringhe.
     * Usata dal frontend per popolare la select "provincia" nei form di registrazione.
     *
     * @return lista delle sigle di tutte le province italiane
     */
    public List<String> getProvince() {
        return Arrays.asList(
            "AG","AL","AN","AO","AQ","AR","AP","AT","AV",
            "BA","BT","BL","BN","BG","BI","BO","BZ","BS","BR",
            "CA","CL","CB","CI","CE","CT","CZ",
            "CH","CO","CS","CR","KR","CN",
            "EN","FM","FE","FI","FG","FC","FR",
            "GE","GO","GR",
            "IM","IS",
            "SP","LT","LE","LC","LI","LO","LU",
            "MC","MN","MS","MT","VS","ME","MI","MO","MB",
            "NA","NO","NU",
            "OG","OT","OR",
            "PD","PA","PR","PV","PG","PU","PE","PC","PI","PT","PN","PZ","PO",
            "RG","RA","RC","RE","RI","RN","RO","RM",
            "SA","SS","SV","SI","SR","SO",
            "TA","TE","TR","TO","TP","TN","TV","TS",
            "UD",
            "VA","VE","VB","VC","VR","VV","VI","VT"
        );
    }
}
