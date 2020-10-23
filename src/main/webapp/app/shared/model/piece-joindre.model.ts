import { IDemande } from 'app/shared/model/demande.model';

export interface IPieceJoindre {
  id?: number;
  nordre?: number;
  name?: string;
  url?: string;
  demande?: IDemande;
}

export class PieceJoindre implements IPieceJoindre {
  constructor(public id?: number, public nordre?: number, public name?: string, public url?: string, public demande?: IDemande) {}
}
