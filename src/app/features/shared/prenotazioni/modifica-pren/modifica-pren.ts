import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PrenotazioneVista } from '../../../../modelli/prenotazione-vista.model';

// Simuliamo il database
const PRENOTAZIONI_DB: PrenotazioneVista[] = [
  { codiceId: 1001, nomeCane: 'Rex', nMicrochip: '380000000000001', categoriaServizio: 'Passeggiata', dataSvolgimento: '2025-06-10', oraSvolgimento: '09:00', prezzoPattuto: 15, usernameCliente: 'giulia.bianchi', usernameDogsitter: 'mario.rossi' },
  { codiceId: 1002, nomeCane: 'Luna', nMicrochip: '380000000000002', categoriaServizio: 'Addestramento', dataSvolgimento: '2025-06-12', oraSvolgimento: '11:00', prezzoPattuto: 25, usernameCliente: 'marco.verdi' },
  { codiceId: 2001, nomeCane: 'Rex', nMicrochip: '380000000000001', categoriaServizio: 'Passeggiata', dataSvolgimento: '2025-06-10', oraSvolgimento: '09:00', prezzoPattuto: 15, usernameCliente: 'giulia.bianchi', usernameDogsitter: 'mario.rossi' }
];

@Component({
  selector: 'app-modifica-pren',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modifica-pren.html',
  styleUrl: './modifica-pren.css'
})
export class ModificaPren implements OnInit {
  
  prenotazione: PrenotazioneVista | null = null;
  ruolo: 'cliente' | 'dogsitter' = 'cliente';
  
  // Array finto di cani del cliente (per la select della modifica cane)
  caniDelCliente = ['Rex', 'Luna', 'Fido', 'Pallino'];

  constructor(
    private route: ActivatedRoute, // Serve per leggere id e ruolo dall'URL
    private router: Router         // Serve per tornare indietro
  ) {}

  // Quando la pagina viene caricata
  ngOnInit(): void {
    // Leggiamo i parametri dall'URL passati dalla pagina precedente
    this.route.queryParams.subscribe(params => {
      const id = Number(params['id']);
      this.ruolo = params['ruolo'] || 'cliente';

      // Cerchiamo la prenotazione nel DB finto
      const trovata = PRENOTAZIONI_DB.find(p => p.codiceId === id);
      if (trovata) {
        // Facciamo una copia scollegata così se modifichiamo ma poi non salviamo, il DB originale non si sporca
        this.prenotazione = { ...trovata };
      }
    });
  }

  // --- LOGICHE DI PERMESSO ---

  // Ritorna TRUE se l'utente può modificare il CANE
  // Regola: Solo il CLIENTE può modificare il cane, e SOLO per servizi Dogsitter (non Addestramento Campo)
  get puoModificareCane(): boolean {
    if (this.ruolo !== 'cliente') return false; // Il dogsitter non può cambiare il cane
    if (this.prenotazione?.categoriaServizio === 'Addestramento') return false; // Al campo non puoi cambiare cane al volo, devi cancellare e riprenotare solitamente
    return true;
  }

  // Ritorna TRUE se si tratta di una prenotazione di un Campo di Addestramento
  get isLezioneCampo(): boolean {
    return this.prenotazione?.categoriaServizio === 'Addestramento';
  }

  // --- AZIONI ---

  salva(): void {
    // Finta validazione: Se il cliente cambia data/ora, in un'app vera dovremmo
    // fare una chiamata HTTP per verificare la disponibilità del Dog Sitter o del Campo.
    if (this.ruolo === 'cliente' && !this.isLezioneCampo) {
      console.log('Controllo disponibilità Dog Sitter in corso (simulato)...');
    }

    console.log('Salvataggio effettuato con i seguenti dati:', this.prenotazione);
    alert('Prenotazione modificata con successo!');
    this.tornaIndietro();
  }

  tornaIndietro(): void {
    // Torna alla lista prenotazioni basandosi sul ruolo
    const rotta = this.ruolo === 'cliente' ? '/home-cliente/prenotazioni' : '/home-dogsitter/prenotazioni';
    this.router.navigate([rotta]);
  }
}
