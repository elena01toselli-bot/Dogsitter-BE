import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Utente } from '../../../modelli/utente.model';
import { ProfiloCliente } from './profilo-cliente/profilo-cliente';
import { ProfiloDogsitter } from './profilo-dogsitter/profilo-dogsitter';

@Component({
  selector: 'app-profilo',
  standalone: true,
  imports: [CommonModule, FormsModule, ProfiloCliente, ProfiloDogsitter],
  templateUrl: './profilo.html',
  styleUrl: './profilo.css',
})
export class Profilo implements OnInit {

  ruolo: 'dogsitter' | 'cliente' = 'cliente';
  utente: Utente | null = null; // Gestisce solo i dati comuni di Utente

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.data.subscribe(data => {
      this.ruolo = data['ruolo'] || 'cliente';
      this.caricaDatiComuni();
    });
  }

  private caricaDatiComuni(): void {
    // TODO: Quando avrai il backend, sostituirai questo blocco con una chiamata API.
    // Esempio futuro: this.http.get<Utente>(`api/utenti/${idLoggato}`).subscribe(...)
    
    // Per ora peschiamo i dati dall'array mock in fondo al file per testare la grafica
    if (this.ruolo === 'dogsitter') {
      this.utente = UTENTI_MOCK.find(u => u.username === 'mario.rossi') || null;
    } else {
      this.utente = UTENTI_MOCK.find(u => u.username === 'valerio.verdi') || null;
    }
  }

  salvaAnagrafica(): void {
    // TODO: In futuro qui farai una chiamata API come this.http.put('api/utenti', this.utente)
    console.log('Dati pronti per essere inviati al backend:', this.utente);
    alert('Informazioni personali comuni salvate con successo!');
  }
}

// ═══════════════════════════════════════════════════════════════════════
// DATI MOCK PER TEST FRONTEND (Da eliminare quando si collegherà il Backend)
// ═══════════════════════════════════════════════════════════════════════

const UTENTI_MOCK: Utente[] = [
  { 
    username: 'mario.rossi', 
    nomeBattesimo: 'Mario', 
    cognome: 'Rossi',  
    cap: '47521', 
    nCivico: '5', 
    provincia: 'FC', 
    via: 'Via Mazzini', 
    nTel: '3331234567', 
    password: '***' 
  },
  { 
    username: 'valerio.verdi', 
    nomeBattesimo: 'Valerio', 
    cognome: 'Verdi',  
    cap: '47521', 
    nCivico: '12', 
    provincia: 'FC', 
    via: 'Via Garibaldi', 
    nTel: '3471122334', 
    password: '***' 
  }
];