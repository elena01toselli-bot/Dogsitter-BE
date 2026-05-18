import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EsecuzioneServizio } from '../../../../modelli/prenotazione.model';
import { Recensione } from '../../../../modelli/recensione.model';

@Component({
  selector: 'app-profilo-dogsitter',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profilo-dogsitter.html',
  styleUrl: './profilo-dogsitter.css'
})
export class ProfiloDogsitter implements OnInit {
  
  maxCani: number = 3; // Potrebbe essere salvato insieme ai dati base dell'utente
  serviziOfferti: EsecuzioneServizio[] = [];
  recensioni: Recensione[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    // TODO: Sostituire con chiamate API 
    // Esempio: this.http.get<EsecuzioneServizio[]>(`/api/servizi/${idSitter}`)
    this.serviziOfferti = SERVIZI_MOCK.filter(s => s.usernameDogsitter === 'mario.rossi');
    
    // Esempio: this.http.get<Recensione[]>(`/api/recensioni/${idSitter}`)
    this.recensioni = RECENSIONI_MOCK.filter(r => r.usernameDogsitter === 'mario.rossi');
  }

  vaiAFormServizio(): void {
    this.router.navigate(['/home-dogsitter/form-servizio'], { state: { serviziAttuali: this.serviziOfferti } });
  }

  rimuoviServizio(tipo: string): void {
    if (confirm(`Vuoi disattivare e rimuovere il servizio "${tipo}"?`)) {
      // TODO: Sostituire con chiamata API DELETE al backend
      this.serviziOfferti = this.serviziOfferti.filter(s => s.catServizio !== tipo);
      console.log(`Servizio ${tipo} disattivato.`);
    }
  }
}

// ═══════════════════════════════════════════════════════════════════════
// DATI MOCK PER IL SITTER (Da eliminare quando si collegherà il Backend)
// ═══════════════════════════════════════════════════════════════════════

const SERVIZI_MOCK: EsecuzioneServizio[] = [
  { codiceIdPrenotazione: 1, catServizio: 'Passeggiata diurna', usernameDogsitter: 'mario.rossi', oraSvolgimento: '10:00', dataSvolgimento: '2026-05-20' },
  { codiceIdPrenotazione: 2, catServizio: 'Asilo Notturno', usernameDogsitter: 'mario.rossi', oraSvolgimento: '22:00', dataSvolgimento: '2026-05-21' }
];

const RECENSIONI_MOCK: Recensione[] = [
  { usernameCliente: 'lucia.b', usernameDogsitter: 'mario.rossi', voto: 5, commento: 'Sitter bravissimo, super raccomandato e dolcissimo coi cani!', data: '14/05/2026' },
  { usernameCliente: 'giovanni.t', usernameDogsitter: 'mario.rossi', voto: 4, commento: 'Molto puntuale e professionale. Ha portato a spasso il mio cucciolo senza problemi.', data: '10/04/2026' }
];