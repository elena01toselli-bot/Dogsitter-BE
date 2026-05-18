import { Component } from '@angular/core';
import { Router, RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-dogsitter',
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './home-dogsitter.html',
  styleUrl: './home-dogsitter.css',
})
export class HomeDogsitter {

  // Nome del dogsitter loggato — per ora fisso, poi verrà dal servizio di autenticazione
  nomeDogsitter: string = 'Mario Rossi';

  // Controlla se la sidebar è aperta o chiusa (utile su schermi piccoli)
  sidebarAperta: boolean = true;

  constructor(private router: Router) {}

  // Naviga alla pagina di login e "disconnette" il dogsitter
  logout(): void {
    // In futuro qui chiamerai il servizio di autenticazione per cancellare il token
    this.router.navigate(['/login']);
  }

  toggleSidebar(): void {
    this.sidebarAperta = !this.sidebarAperta;
  }
}