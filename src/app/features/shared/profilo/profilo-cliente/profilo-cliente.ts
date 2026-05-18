import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Cane } from '../../../../modelli/cane.model';

@Component({
  selector: 'app-profilo-cliente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profilo-cliente.html',
  styleUrl: './profilo-cliente.css'
})
export class ProfiloCliente implements OnInit {
  
  caniUtente: Cane[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    // TODO: Sostituire con chiamata API (es. this.http.get<Cane[]>(`/api/cani/cliente/${id}`))
    // Per ora filtriamo i dati mock per il cliente "valerio.verdi"
    this.caniUtente = CANI_MOCK.filter(c => c.usernameCliente === 'valerio.verdi');
  }

  vaiAFormCane(caneDaModificare?: Cane): void {
    if (caneDaModificare) {
      this.router.navigate(['/home-cliente/form-cane'], { state: { cane: caneDaModificare } });
    } else {
      this.router.navigate(['/home-cliente/form-cane']);
    }
  }

  eliminaCane(microchip: string): void {
    if (confirm('Sei sicuro di voler rimuovere questo cane dalla tua lista anagrafica?')) {
      // TODO: Sostituire con chiamata API DELETE (es. this.http.delete(`/api/cani/${microchip}`))
      this.caniUtente = this.caniUtente.filter(c => c.nMicrochip !== microchip);
      console.log(`Cane con microchip ${microchip} eliminato.`);
    }
  }
}

// ═══════════════════════════════════════════════════════════════════════
// DATI MOCK PER I CANI (Da eliminare quando si collegherà il Backend)
// ═══════════════════════════════════════════════════════════════════════
const CANI_MOCK: Cane[] = [
  { nMicrochip: '123456789012345', nome: 'Zelda', razza: 'Border Collie', taglia: 'Media', dataNascita: '2022-04-10', noteComportamento: 'Molto giocherellona', usernameCliente: 'valerio.verdi' },
  { nMicrochip: '987654321098765', nome: 'Thor', razza: 'Pastore Tedesco', taglia: 'Grande', dataNascita: '2020-11-22', noteComportamento: 'Protettivo ma docile', usernameCliente: 'valerio.verdi' }
];