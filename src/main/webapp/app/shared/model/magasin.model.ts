import { IDemande } from 'app/shared/model/demande.model';
import { IMagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';

export interface IMagasin {
  id?: number;
  code?: string;
  libelle?: string;
  rtr?: string;
  demandes?: IDemande[];
  magasinUtilisateurs?: IMagasinUtilisateur[];
}

export class Magasin implements IMagasin {
  constructor(
    public id?: number,
    public code?: string,
    public libelle?: string,
    public rtr?: string,
    public demandes?: IDemande[],
    public magasinUtilisateurs?: IMagasinUtilisateur[]
  ) {}
}
