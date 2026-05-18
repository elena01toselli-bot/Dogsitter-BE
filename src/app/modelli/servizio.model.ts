export interface Servizio {
  categoria: string;
  durataMedia: number;
}

export interface Offre {
  usernameDogsitter: string;     // FK → DOG_SITTER
  catServizio: string;           // FK → SERVIZIO
  prezzoListino: number;
}