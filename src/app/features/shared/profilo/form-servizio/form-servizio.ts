import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EsecuzioneServizio } from '../../../../modelli/prenotazione.model';

@Component({
  selector: 'app-form-servizio',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-servizio.html',
  styleUrl: './form-servizio.css'
})
export class FormServizio {
  // 1. Lista fissa globale di tutte le tipologie ammesse dal sistema (es. memorizzate sul DB)
  tipologieGlobaliDB: string[] = ['Passeggiata diurna', 'Asilo Notturno', 'Toelettatura a domicilio', 'Addestramento base', 'Pensione completa'];
  
  // Lista dei servizi che il dog sitter eroga già (ricevuti dal profilo)
  serviziAttualiSitter: EsecuzioneServizio[] = [];
  
  servizioSelezionato: string = '';

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state && navigation.extras.state['serviziAttuali']) {
      this.serviziAttualiSitter = navigation.extras.state['serviziAttuali'];
    }
  }

  // 🔥 LOGICA CORE: Restituisce solo le tipologie del DB che NON sono già presenti nei servizi del sitter
  get tipologieDisponibili(): string[] {
    return this.tipologieGlobaliDB.filter(tipoGlobale => 
      !this.serviziAttualiSitter.some(servizioAttivo => servizioAttivo.catServizio === tipoGlobale)
    );
  }

  aggiungi(): void {
    if (!this.servizioSelezionato) {
      alert('Seleziona una tipologia di servizio!');
      return;
    }
    console.log('Aggiunta servizio sul DB:', this.servizioSelezionato);
    this.router.navigate(['/home-dogsitter/profilo']);
  }

  annulla(): void {
    this.router.navigate(['/home-dogsitter/profilo']);
  }
}