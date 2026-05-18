import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PrenotazioneVista } from '../../../modelli/prenotazione-vista.model';

const PRENOTAZIONI_FINTE_DS: PrenotazioneVista[] = [
  { codiceId: 1001, nomeCane: 'Rex',     nMicrochip: '380000000000001', categoriaServizio: 'Passeggiata',  dataSvolgimento: '2025-06-10', oraSvolgimento: '09:00', prezzoPattuto: 15, usernameCliente: 'giulia.bianchi' },
  { codiceId: 1002, nomeCane: 'Luna',    nMicrochip: '380000000000002', categoriaServizio: 'Toelettatura', dataSvolgimento: '2025-06-12', oraSvolgimento: '11:00', prezzoPattuto: 25, usernameCliente: 'marco.verdi' },
  { codiceId: 1003, nomeCane: 'Fido',    nMicrochip: '380000000000003', categoriaServizio: 'Custodia',     dataSvolgimento: '2025-06-15', oraSvolgimento: '08:00', prezzoPattuto: 40, usernameCliente: 'anna.rossi' },
  { codiceId: 1004, nomeCane: 'Birba',   nMicrochip: '380000000000004', categoriaServizio: 'Passeggiata',  dataSvolgimento: '2025-06-18', oraSvolgimento: '17:00', prezzoPattuto: 15, usernameCliente: 'luca.ferrari' },
  { codiceId: 1005, nomeCane: 'Pallino', nMicrochip: '380000000000005', categoriaServizio: 'Toelettatura', dataSvolgimento: '2025-06-20', oraSvolgimento: '10:00', prezzoPattuto: 25, usernameCliente: 'giulia.bianchi' },
];

const PRENOTAZIONI_FINTE_CLIENTE: PrenotazioneVista[] = [
  { codiceId: 2001, nomeCane: 'Rex',  nMicrochip: '380000000000001', categoriaServizio: 'Passeggiata',  dataSvolgimento: '2025-06-10', oraSvolgimento: '09:00', prezzoPattuto: 15, usernameDogsitter: 'mario.rossi' },
  { codiceId: 2002, nomeCane: 'Rex',  nMicrochip: '380000000000001', categoriaServizio: 'Custodia',     dataSvolgimento: '2025-06-14', oraSvolgimento: '08:00', prezzoPattuto: 40, usernameDogsitter: 'sara.neri' },
  { codiceId: 2003, nomeCane: 'Luna', nMicrochip: '380000000000006', categoriaServizio: 'Toelettatura', dataSvolgimento: '2025-06-20', oraSvolgimento: '11:00', prezzoPattuto: 25, usernameDogsitter: 'mario.rossi' },
];

const CATEGORIE: string[] = ['Passeggiata', 'Toelettatura', 'Custodia', 'Addestramento'];

@Component({
  selector: 'app-prenotazioni',
  imports: [CommonModule, FormsModule],
  templateUrl: './prenotazioni.html',
  styleUrl: './prenotazioni.css',
})
export class Prenotazioni implements OnInit {

  ruolo: 'dogsitter' | 'cliente' = 'dogsitter';

  tutteLePrenotazioni: PrenotazioneVista[] = [];
  prenotazioniFiltrate: PrenotazioneVista[] = [];

  ricercaTesto: string = '';
  filtroCategoria: string = '';
  filtroData: string = '';
  categorie: string[] = CATEGORIE;

  get haFiltriAttivi(): boolean {
    return this.ricercaTesto !== '' || this.filtroCategoria !== '' || this.filtroData !== '';
  }

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.ruolo = this.route.snapshot.data['ruolo'] || 'dogsitter';
    this.caricaPrenotazioni();
  }

  caricaPrenotazioni(): void {
    if (this.ruolo === 'dogsitter') {
      this.tutteLePrenotazioni = [...PRENOTAZIONI_FINTE_DS];
    } else {
      this.tutteLePrenotazioni = [...PRENOTAZIONI_FINTE_CLIENTE];
    }
    this.prenotazioniFiltrate = [...this.tutteLePrenotazioni];
  }

  // Applica i filtri di ricerca (testo, categoria, data)
  applica(): void {
    const testo = this.ricercaTesto.toLowerCase().trim();
    this.prenotazioniFiltrate = this.tutteLePrenotazioni.filter(p => {
      // Controlla se il testo digitato è presente nell'ID, nome del cane o microchip
      const corrispondeTesto =
        testo === '' ||
        p.codiceId.toString().includes(testo) ||
        p.nomeCane.toLowerCase().includes(testo) ||
        p.nMicrochip.includes(testo);
        
      // Controlla se la categoria selezionata coincide
      const corrispondeCategoria =
        this.filtroCategoria === '' || p.categoriaServizio === this.filtroCategoria;
        
      // Controlla se la data selezionata coincide
      const corrispondeData =
        this.filtroData === '' || p.dataSvolgimento === this.filtroData;
        
      // La prenotazione viene mostrata solo se passa tutti e tre i filtri
      return corrispondeTesto && corrispondeCategoria && corrispondeData;
    });
  }

  // Svuota tutti i filtri e mostra l'elenco completo
  reset(): void {
    this.ricercaTesto = '';
    this.filtroCategoria = '';
    this.filtroData = '';
    this.prenotazioniFiltrate = [...this.tutteLePrenotazioni];
  }

  // Metodo per navigare alla pagina di modifica della prenotazione.
  // Passiamo il codiceId e il ruolo attuale (cliente/dogsitter) come query params
  // così la pagina di destinazione sa come comportarsi e cosa caricare.
  modifica(prenotazione: PrenotazioneVista): void {
    this.router.navigate(['/modifica-prenotazione'], {
      queryParams: { id: prenotazione.codiceId, ruolo: this.ruolo }
    });
  }

  // Elimina una prenotazione
  elimina(prenotazione: PrenotazioneVista): void {
    // confirm() mostra un popup nativo del browser chiedendo Ok/Annulla
    if (!confirm(`Eliminare la prenotazione #${prenotazione.codiceId}?`)) return;
    
    // Filtriamo l'array rimuovendo l'elemento che ha quello specifico codiceId
    this.tutteLePrenotazioni = this.tutteLePrenotazioni.filter(p => p.codiceId !== prenotazione.codiceId);
    
    // Riapplichiamo i filtri per aggiornare la visualizzazione
    this.applica();
  }

  // Porta alla pagina per creare una nuova prenotazione
  nuovaPrenotazione(): void {
    this.router.navigate(['/modifica-prenotazione'], {
      // Passiamo 'nuova' come id per dire alla pagina di preparare un form vuoto
      queryParams: { id: 'nuova', ruolo: 'cliente' }
    });
  }

  // Formatta la data dal formato YYYY-MM-DD al formato europeo DD/MM/YYYY
  formattaData(data: string): string {
    if (!data) return '';
    const [anno, mese, giorno] = data.split('-');
    return `${giorno}/${mese}/${anno}`;
  }
}