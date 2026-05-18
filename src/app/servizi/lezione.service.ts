import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Lezione } from '../modelli/lezioni.model'; // Regola il percorso se necessario

@Injectable({
  providedIn: 'root'
})
export class LezioneService {

  // Endpoint base per le API delle lezioni nel backend
  private apiUrl = 'http://localhost:8080/api/lezioni';

  constructor(private http: HttpClient) { }

  // Recupera tutte le lezioni associate a uno specifico campo di addestramento
  getByCampo(nomeCampo: string): Observable<Lezione[]> {
    // Inviamo una richiesta GET al backend passandogli il nome del campo come parametro
    return this.http.get<Lezione[]>(`${this.apiUrl}/campo/${nomeCampo}`);
  }

  // Modifica una lezione esistente sul database
  update(lezione: Lezione): Observable<Lezione> {
    // Usiamo il metodo PUT per aggiornare la risorsa sul server, passando l'oggetto nel corpo della richiesta
    return this.http.put<Lezione>(this.apiUrl, lezione);
  }
}