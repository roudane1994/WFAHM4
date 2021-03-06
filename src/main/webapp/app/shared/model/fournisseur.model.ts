import { IDemande } from 'app/shared/model/demande.model';

export interface IFournisseur {
  id?: number;
  libelle?: string;
  demandes?: IDemande[];
  demandes?: IDemande[];
}

export class Fournisseur implements IFournisseur {
  constructor(public id?: number, public libelle?: string, public demandes?: IDemande[], public demandes?: IDemande[]) {}
}
