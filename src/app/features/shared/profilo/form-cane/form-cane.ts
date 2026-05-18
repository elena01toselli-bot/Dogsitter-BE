import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Cane } from '../../../../modelli/cane.model';

@Component({
  selector: 'app-form-cane',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-cane.html',
  styleUrl: './form-cane.css'
})
export class FormCane implements OnInit {
  inModifica: boolean = false;
  cane: Cane = { nMicrochip: '', nome: '', razza: '', taglia: 'Media', dataNascita: '', noteComportamento: '', usernameCliente: '' };

  constructor(private router: Router) {
    // Recuperiamo i dati passati dal profilo prima che la pagina si carichi completamente
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state && navigation.extras.state['cane']) {
      this.cane = { ...navigation.extras.state['cane'] }; // Clona l'oggetto per non modificare l'originale prima del salvataggio
      this.inModifica = true; // Attiva la modalità modifica (campi pieni)
    }
  }

  ngOnInit(): void {}

  salva(): void {
    if (!this.cane.nome || !this.cane.nMicrochip) {
      alert('I campi Nome e Microchip sono obbligatori!');
      return;
    }
    
    if (this.inModifica) {
      console.log('Salvataggio modifiche cane nel DB:', this.cane);
    } else {
      console.log('Inserimento nuovo cane nel DB:', this.cane);
    }

    // Torna indietro al profilo del cliente dopo il salvataggio
    this.router.navigate(['/home-cliente/profilo']);
  }

  annulla(): void {
    this.router.navigate(['/home-cliente/profilo']);
  }
}